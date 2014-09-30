CREATE OR REPLACE 
procedure proc_credit_month is 
TYPE ref_cursor_type IS REF CURSOR;
last_month varchar2(6);
last_last_month varchar2(6);
v_sql varchar2(1000);
c ref_cursor_type;
TYPE query is record  
(
    user_id EDU_USER_CREDITS_MONTH.user_id%TYPE,
    credit_add EDU_USER_CREDITS_MONTH.credit_add%TYPE,
    credit_consume  EDU_USER_CREDITS_MONTH.credit_consume%TYPE,
    credit_usable  EDU_USER_CREDITS_MONTH.credit_usable%TYPE
);
v query;
begin       
    last_month := to_char(add_months(trunc(sysdate),-1),'YYYYMM');--上个月
        last_last_month := to_char(add_months(trunc(sysdate),-2),'YYYYMM');--上上个月
        delete from EDU_USER_CREDITS_MONTH where month = last_month;
    v_sql := 'select d.user_id,d.credit_add,d.credit_consume, d2.credit_usable from 
    (select count(d1.user_id) num,d1.user_id,sum(d1.credit_add) credit_add,sum(d1.credit_consume) credit_consume,max(d1.day) max_day
    from EDU_USER_CREDITS_DAY partition(P'||last_month||') d1 where d1.DAY< to_char(sysdate,''YYYYMMDD'') GROUP BY D1.USER_ID) d
    LEFT JOIN EDU_USER_CREDITS_DAY partition(P'||last_month||') d2 on d2.user_id=d.user_id and d2.day= d.max_day';
    open c for v_sql;
    loop
        fetch c into v;
        exit when c%notfound;
        insert into EDU_USER_CREDITS_MONTH values (SEQ_USER_CREDIT.NEXTVAL, v.user_id, v.credit_usable, v.credit_consume, v.credit_add, last_month);
    end loop;
    close c;
        --插入上上个月有但上个月没有的用户数据
        insert into EDU_USER_CREDITS_MONTH select * from EDU_USER_CREDITS_MONTH m1 where m1.month = last_last_month and 
        not exists(select null from EDU_USER_CREDITS_MONTH where month = last_month and user_id=M1.user_id);
        --DBMS_OUTPUT.PUT_LINE('test');
    commit;
end;


declare job_id pls_integer; 
begin
sys.dbms_job.submit(job => job_id,
                      what => 'proc_credit_month;',
                      next_date => sysdate,
                      interval => 'TRUNC(LAST_DAY(SYSDATE ) + 1) + 1/24');
commit;
end;
/

/*declare job_id pls_integer; 
begin
sys.dbms_job.submit(job => job_id,
                      what => 'proc_credit_month;',
                      next_date => to_date('25-09-2014 16:45:00', 'dd-mm-yyyy hh24:mi:ss'),
                      interval => 'TRUNC(LAST_DAY(SYSDATE ) + 1) + 1/24');
commit;
end;
/*/


 
select * from sys.user_jobs;  

-- begin  
-- sys.dbms_job.remove(43);  
-- commit;  
-- end; 



--描述                    INTERVAL参数值 
--每天午夜12点            'TRUNC(SYSDATE + 1)' 
--每天早上8点30分         'TRUNC(SYSDATE + 1) + （8*60+30）/(24*60)' 
--每星期二中午12点         'NEXT_DAY(TRUNC(SYSDATE ), ''TUESDAY'' ) + 12/24' 
--每个月第一天的午夜12点    'TRUNC(LAST_DAY(SYSDATE ) + 1)' 
--每个季度最后一天的晚上11点 'TRUNC(ADD_MONTHS(SYSDATE + 2/24, 3 ), 'Q' ) -1/24' 
--每星期六和日早上6点10分    'TRUNC(LEAST(NEXT_DAY(SYSDATE, ''SATURDAY"), NEXT_DAY(SYSDATE, "SUNDAY"))) + （6×60+10）/（24×60）'