/***************************************************************************************
*用户积分相关数据库定义对象
**************************************************************************************/
--积分系统内部用到的序列
create sequence SEQ_USER_CREDIT
minvalue 20000000000
maxvalue 999999999999999
start with 20000000000
increment by 1
cache 20;
/***************************************************************************************
*用户积分（总表）实时加月结
**************************************************************************************/

create table EDU_USER_CREDITS
(
  ID                NUMBER(16) not null,
  USER_ID           NUMBER(16) not null,
  CREDIT_USABLE     NUMBER(16) default 0 not null,
  CREDIT_CONSUME    NUMBER(16) default 0 not null,
  CREDIT_BECOME_DUE NUMBER(16) default 0 not null,
  CREATE_AT         DATE default SYSDATE not null,
  UPDATE_AT         DATE default SYSDATE not null
)
;
comment on table EDU_USER_CREDITS
is '用户积分（总表）实时加月结';
comment on column USER_CREDITS.CREDIT_USABLE
is '可用积分';
comment on column USER_CREDITS.CREDIT_CONSUME
is '用户消费积分数目，用于结算月积分，结算之后置为0';
comment on column USER_CREDITS.CREDIT_BECOME_DUE
is '即将到期积分（提示即将到期的月份，每月初月结一次）';
alter table EDU_USER_CREDITS
add constraint PK_USER_CREDITS primary key (ID);
alter table EDU_USER_CREDITS
add constraint UK1_USER_CREDITS unique (USER_ID);
/***************************************************************************************
*用户积分（月表）
**************************************************************************************/

create table EDU_USER_CREDITS_MONTH
(
  ID                NUMBER(16) not null,
  USER_ID           NUMBER(16) not null,
  CREDIT_USABLE     NUMBER(16) default 0 not null,
  CREDIT_CONSUME    NUMBER(16) default 0 not null,
  CREDIT_ADD        NUMBER(16) default 0 not null,
  MONTH             VARCHAR2(6) default TO_CHAR(SYSDATE,'YYYYMM') not null
)
;
comment on table EDU_USER_CREDITS_MONTH
is '用户积分（月表）';
comment on column EDU_USER_CREDITS_MONTH.CREDIT_USABLE
is '可用积分';
comment on column EDU_USER_CREDITS_MONTH.CREDIT_CONSUME
is '用户消费积分数目';
comment on column EDU_USER_CREDITS_MONTH.CREDIT_ADD
is '用户增加积分数目';
comment on column EDU_USER_CREDITS_MONTH.MONTH
is '年月YYYYMM';
alter table EDU_USER_CREDITS_MONTH
add constraint PK_EDU_USER_CREDITS_MONTH primary key (ID);
alter table EDU_USER_CREDITS_MONTH
add constraint UK1_EDU_USER_CREDITS_MONTH unique (USER_ID, MONTH);
/***************************************************************************************
*用户积分（日统计表）
**************************************************************************************/

create table EDU_USER_CREDITS_DAY
(
  ID                NUMBER(16) not null,
  USER_ID           NUMBER(16) not null,
  CREDIT_USABLE     NUMBER(16) default 0 not null,
  CREDIT_CONSUME    NUMBER(16) default 0 not null,
  CREDIT_ADD        NUMBER(16) default 0 not null,
  DAY               VARCHAR2(8) default TO_CHAR(SYSDATE,'YYYYMMDD') not null
)
partition by range (DAY)
(  
  partition P201406 values less than ('20140701')
  tablespace DATA_DIDU_TBS,
  partition P201407 values less than ('20140801')
  tablespace DATA_DIDU_TBS,
  partition P201408 values less than ('20140901')
  tablespace DATA_DIDU_TBS,
  partition P201409 values less than ('20141001')
  tablespace DATA_DIDU_TBS,
  partition P201410 values less than ('20141101')
  tablespace DATA_DIDU_TBS,
  partition P201411 values less than ('20141201')
  tablespace DATA_DIDU_TBS,
  partition P201412 values less than ('20150101')
  tablespace DATA_DIDU_TBS,
  partition P201501 values less than ('20150201')
  tablespace DATA_DIDU_TBS,
  partition P201502 values less than ('20150301')
  tablespace DATA_DIDU_TBS,
  partition P201503 values less than ('20150401')
  tablespace DATA_DIDU_TBS,
  partition P201504 values less than ('20150501')
  tablespace DATA_DIDU_TBS,
  partition P201505 values less than ('20150601')
  tablespace DATA_DIDU_TBS,
  partition P201506 values less than ('20150701')
  tablespace DATA_DIDU_TBS,
  partition P201507 values less than ('20150801')
  tablespace DATA_DIDU_TBS,
  partition P201508 values less than ('20150901')
  tablespace DATA_DIDU_TBS,
  partition P201509 values less than ('20151001')
  tablespace DATA_DIDU_TBS,
  partition P201510 values less than ('20151101')
  tablespace DATA_DIDU_TBS,
  partition P201511 values less than ('20151201')
  tablespace DATA_DIDU_TBS,
  partition P201512 values less than ('20160101')
  tablespace DATA_DIDU_TBS,
  partition P201601 values less than ('20160201')
  tablespace DATA_DIDU_TBS,
  partition P201602 values less than ('20160301')
  tablespace DATA_DIDU_TBS,
  partition P201603 values less than ('20160401')
  tablespace DATA_DIDU_TBS,
  partition P201604 values less than ('20160501')
  tablespace DATA_DIDU_TBS,
  partition P201605 values less than ('20160601')
  tablespace DATA_DIDU_TBS,
  partition P201606 values less than ('20160701')
  tablespace DATA_DIDU_TBS,
  partition P201607 values less than ('20160801')
  tablespace DATA_DIDU_TBS,
  partition P201608 values less than ('20160901')
  tablespace DATA_DIDU_TBS,
  partition P201609 values less than ('20161001')
  tablespace DATA_DIDU_TBS,
  partition P201610 values less than ('20161101')
  tablespace DATA_DIDU_TBS,
  partition P201611 values less than ('20161201')
  tablespace DATA_DIDU_TBS,
  partition P201612 values less than ('20170101')
  tablespace DATA_DIDU_TBS,
  partition P201701 values less than ('20170201')
  tablespace DATA_DIDU_TBS,
  partition P201702 values less than ('20170301')
  tablespace DATA_DIDU_TBS,
  partition P201703 values less than ('20170401')
  tablespace DATA_DIDU_TBS,
  partition P201704 values less than ('20170501')
  tablespace DATA_DIDU_TBS,
  partition P201705 values less than ('20170601')
  tablespace DATA_DIDU_TBS,
  partition P201706 values less than ('20170701')
  tablespace DATA_DIDU_TBS,
  partition P201707 values less than ('20170801')
  tablespace DATA_DIDU_TBS,
  partition POTHER values less than ('20990101')
  tablespace DATA_DIDU_TBS

);
comment on table EDU_USER_CREDITS_DAY
is '用户积分（日统计表）';
comment on column EDU_USER_CREDITS_DAY.CREDIT_USABLE
is '可用积分';
comment on column EDU_USER_CREDITS_DAY.CREDIT_CONSUME
is '用户消费积分数目';
comment on column EDU_USER_CREDITS_DAY.CREDIT_ADD
is '用户增加积分数目';
comment on column EDU_USER_CREDITS_DAY.DAY
is '日期YYYYMMDD';
alter table EDU_USER_CREDITS_DAY
add constraint PK_EDU_USER_CREDITS_DAY primary key (ID);
alter table EDU_USER_CREDITS_DAY
add constraint UK1_EDU_USER_CREDITS_DAY unique (USER_ID, DAY);
/***************************************************************************************
*应用操作积分策略定义表
**************************************************************************************/
create table EDU_SYS_CREDIT_STRATEGY
(
  ID               NUMBER(16) not null,
  NAME             VARCHAR2(200) not null,
  APP_ID           NUMBER(16) not null,
  APP_NAME         VARCHAR2(200) not null,
  ACTION_ID        NUMBER(16) not null,
  ACTION_NAME      VARCHAR2(200) not null,
  CREDIT_NUM       NUMBER(16) default 1 not null,
  CREDIT_STRATEGY  VARCHAR2(200) default 'dailyLimitCreditStrategy' not null,
  CREDIT_LIMIT     NUMBER(16) default 20 not null,
  CREDIT_MULTIPLY  NUMBER(16),
  CREDIT_CONTINUED NUMBER(16),
  CREDIT_START     DATE,
  CREDIT_END       DATE,
  CREATE_BY        NUMBER(16) default 1 not null,
  CREATE_AT        DATE default SYSDATE not null,
  UPDATE_BY        NUMBER(16),
  UPDATE_AT        DATE
)
;

comment on table EDU_SYS_CREDIT_STRATEGY
is '应用操作积分策略定义表';
comment on column EDU_SYS_CREDIT_STRATEGY.APP_ID
is '应用ID';
comment on column EDU_SYS_CREDIT_STRATEGY.ACTION_ID
is '操作ID';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_NUM
is '单次操作积分值';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_STRATEGY
is '积分策略定制';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_LIMIT
is '积分限制，根据积分策略不同，为对应的不同限制值';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_MULTIPLY
is '多倍积分，应用于活动或多倍积分策略';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_CONTINUED
is '用于连续签到的策略，最多连续多少天会有加权';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_START
is '辅助字段 特殊积分策略 有效时段 ，例如 多倍';
comment on column EDU_SYS_CREDIT_STRATEGY.CREDIT_END
is '辅助字段 特殊积分策略 有效时段例如 多倍';
alter table EDU_SYS_CREDIT_STRATEGY
add constraint PK_APP_ACTION_CREDIT primary key (ID);
alter table EDU_SYS_CREDIT_STRATEGY
add constraint UK_APP_ACTION_CREDIT unique (APP_ID, ACTION_ID);

-- Add/modify columns
alter table EDU_SYS_CREDIT_STRATEGY add USER_TYPE number(16) default 0;
-- Add comments to the columns
comment on column EDU_SYS_CREDIT_STRATEGY.USER_TYPE
is '0通用 1 老师 2 学生 3 家长 4 网校开放注册用户 5 校讯通实时开通guest用户(数据同步后作废) 6 第三方互联网用户 7 测评用户_游客  8 网校教育联盟用户';


/***************************************************************************************
*用户积分明细表 （分区表 范围分区，每月一个分区）
**************************************************************************************/
create table EDU_USER_CREDIT_DETAILS
(
  ID          NUMBER(16) not null,
  USER_ID     NUMBER(16) not null,
  CREDIT_NUM  NUMBER(16) not null,
  APP_ID      NUMBER(16) not null,
  ACTION_ID   NUMBER(16) not null,
  TYPE        NUMBER(1) not null,
  CAUSE       VARCHAR2(500) not null,
  SOURCE_ID   NUMBER(16),
  SOURCE_TYPE VARCHAR2(500),
  NOTE        VARCHAR2(500),
  CREATE_AT   DATE default sysdate not null,
  VERSION     VARCHAR2(56),
  STRATEGY_ID NUMBER(16),
  LOG_ID      NUMBER(16)
)
partition by range (CREATE_AT)
(  
  partition P201406 values less than (TO_DATE(' 2014-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201407 values less than (TO_DATE(' 2014-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201408 values less than (TO_DATE(' 2014-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201409 values less than (TO_DATE(' 2014-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201410 values less than (TO_DATE(' 2014-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201411 values less than (TO_DATE(' 2014-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201412 values less than (TO_DATE(' 2015-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201501 values less than (TO_DATE(' 2015-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201502 values less than (TO_DATE(' 2015-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201503 values less than (TO_DATE(' 2015-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201504 values less than (TO_DATE(' 2015-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201505 values less than (TO_DATE(' 2015-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201506 values less than (TO_DATE(' 2015-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201507 values less than (TO_DATE(' 2015-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201508 values less than (TO_DATE(' 2015-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201509 values less than (TO_DATE(' 2015-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201510 values less than (TO_DATE(' 2015-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201511 values less than (TO_DATE(' 2015-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201512 values less than (TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201601 values less than (TO_DATE(' 2016-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201602 values less than (TO_DATE(' 2016-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201603 values less than (TO_DATE(' 2016-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201604 values less than (TO_DATE(' 2016-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201605 values less than (TO_DATE(' 2016-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201606 values less than (TO_DATE(' 2016-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201607 values less than (TO_DATE(' 2016-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201608 values less than (TO_DATE(' 2016-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201609 values less than (TO_DATE(' 2016-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201610 values less than (TO_DATE(' 2016-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201611 values less than (TO_DATE(' 2016-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201701 values less than (TO_DATE(' 2017-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201702 values less than (TO_DATE(' 2017-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201703 values less than (TO_DATE(' 2017-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201704 values less than (TO_DATE(' 2017-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201705 values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201706 values less than (TO_DATE(' 2017-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition P201707 values less than (TO_DATE(' 2017-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS,
  partition POTHER values less than (TO_DATE(' 2099-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
  tablespace DATA_DIDU_TBS

);
comment on table EDU_USER_CREDIT_DETAILS
is '用户积分明细表';
comment on column EDU_USER_CREDIT_DETAILS.APP_ID
is '应用id';
comment on column EDU_USER_CREDIT_DETAILS.ACTION_ID
is '行为id';
comment on column EDU_USER_CREDIT_DETAILS.TYPE
is '0收入 1支出 其他支出原因';
comment on column EDU_USER_CREDIT_DETAILS.CAUSE
is '原因，对应于 cfg_app_action_credit_strategy 的原因';
comment on column EDU_USER_CREDIT_DETAILS.SOURCE_ID
is '辅助字段 源id';
comment on column EDU_USER_CREDIT_DETAILS.SOURCE_TYPE
is '源id 对应的表名';
comment on column EDU_USER_CREDIT_DETAILS.NOTE
is '备注';
create index IDX1_USER_CREDIT_DETAILS on EDU_USER_CREDIT_DETAILS (USER_ID) LOCAL;
create index UK1_USER_CREDIT_DETAILS on EDU_USER_CREDIT_DETAILS (ID, CREATE_AT) LOCAL;
-- Create/Recreate indexes
create index IDX2_USER_CREDIT_DETAILS on EDU_USER_CREDIT_DETAILS (app_id, action_id) LOCAL;
create index IDX3_USER_CREDIT_DETAILS on EDU_USER_CREDIT_DETAILS (create_at) LOCAL;


/***********************************************************************************
*初始化数据
************************************************************************************/



-- Add comments to the columns
comment on column CFG_APP_ACTION_CREDIT_STRATEGY.CREDIT_LIMIT
is '积分限制，每日积分限制';


/***************************************************************************************
*应用操作积分策略定义版本表
**************************************************************************************/
create table EDU_SYS_STRATEGY_VERSION
(
  ID               NUMBER(16) not null,
  VERSION          VARCHAR2(56) not null,             --版本号yyyyMMddHHmmss
  START_TIME       DATE not null,                     --生效时间
  END_TIME         DATE not null,                     --结束时间
  NEXT_ID          NUMBER(16) null,                   --下一个版本的ID
  STATUS           NUMBER(1) default 0 not null,      --版本状态 1:正在使用 2:尚未使用 3:已经过期
  CREATE_BY        NUMBER(16) default 1 not null,
  CREATE_AT        DATE default SYSDATE not null
)
;



alter table EDU_SYS_CREDIT_STRATEGY add FIRST_CREDIT number(16) default 0;
alter table EDU_SYS_CREDIT_STRATEGY add ACTION_INTERVAL number(16) default 0;
alter table EDU_SYS_CREDIT_STRATEGY add SPECIAL_STRATEGY VARCHAR2(200) null;

comment on column EDU_SYS_CREDIT_STRATEGY.FIRST_CREDIT
is '首次积分';
comment on column EDU_SYS_CREDIT_STRATEGY.ACTION_INTERVAL
is '两次动作间隔时间（秒），动作间隔时间内不没有积分';
comment on column EDU_SYS_CREDIT_STRATEGY.SPECIAL_STRATEGY
is '特殊积分策略，多个策略之间用逗号隔开

1个月内连续登录，根据连续登录的天数，获得天数相应分值*2（教师）:
loginStrategy

连续3天成功完成，可获得50分，当周仅限1次:
continueInWeekStrategy
';
