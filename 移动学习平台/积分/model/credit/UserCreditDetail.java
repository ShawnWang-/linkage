package models.credit;

import play.db.jpa.GenericModel;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: michael
 * Date: 12-12-13
 * Time: 上午10:09
 * Usage:  用户积分明细表（分区表）
 */
@Entity
@Table(name = "USER_CREDIT_DETAILS")
public class UserCreditDetail extends GenericModel{
    @Id
    @SequenceGenerator(name = "SEQ_USER_CREDIT",
            sequenceName = "SEQ_USER_CREDIT",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER_CREDIT")
    @Column(name="ID" ,nullable = false)
    public Long id;

    @Column(name="user_id", nullable = false ,unique = true)
    public Long user_id;

    @Column(name="credit_num", nullable = false)
    public Long credit_num;

    @Column(name = "APP_ID" , nullable = false)
    public Long app_id;     //应用ID

    @Column(name = "ACTION_ID" , nullable = false)
    public Long action_id;     //动作ID

    @Column(name = "type" , nullable = false)
    public Integer type; // 0收入 1支出 其他支出原因

    @Column(name = "CAUSE" ,nullable = false ,length = 500)
    public String cause;     //积分收入的原因

    @Column(name = "SOURCE_ID" )
    public Long source_id;     //产生积分的数据源ID， ex 被评论的心情 ，签到

    @Column(name = "SOURCE_TYPE" ,nullable = false ,length = 500)
    public String source_type;     //对应于源的表名

    @Column(name = "NOTE"  ,length = 500)
    public String note;     //备注

    @Column(name="create_at", nullable = false)
    public Date create_at;     //分区字段

    public UserCreditDetail() {
    }

    public UserCreditDetail(Long user_id,Long credit_num,
                            Long app_id,Long action_id,Integer type,
                            String cause,Long source_id,String source_type,
                            String note){
        this.user_id = user_id;
        this.credit_num = credit_num;
        this.app_id = app_id;
        this.action_id = action_id;
        this.type = type;
        this.cause = cause;
        this.source_id = source_id;
        this.source_type = source_type;
        this.note = note;
        this.create_at = new Date();
    }


    /**
     * 使用分区表查询用户当月积分明细
     * @param user_id
     * @return   List<UserCreditDetail>
     */
    public static List<UserCreditDetail> getCurrentMonthByUserID(Long user_id){
        List<UserCreditDetail> userCreditDetailList = new ArrayList<UserCreditDetail>();
        StringBuilder sqlbud = new StringBuilder();
        sqlbud.append("SELECT * FROM USER_CREDIT_DETAILS ")
                .append(getPartitionByDate(new Date()))
                .append(" WHERE USER_ID = ?");
        Query query = JPA.em().createNativeQuery(sqlbud.toString(),UserCreditDetail.class);
        query.setParameter(1,user_id);
        List<UserCreditDetail> list = query.getResultList();
        return list;
    }

    /**
     * 使用分区表查询用户当天已获取某种积分的总数
     */
    public static Long getCreditByCurrentDay(Long user_id ,Long app_id ,
                                       Long action_id ){
        Long sumCredit;
        StringBuilder sqlbud = new StringBuilder();
        sqlbud.append("SELECT SUM(CREDIT_NUM) FROM USER_CREDIT_DETAILS ")
                .append(getPartitionByDate(new Date()))
                .append(" WHERE USER_ID = ? AND APP_ID = ? AND ACTION_ID = ? AND CREATE_AT >= TRUNC(SYSDATE)");
        Query query = JPA.em().createNativeQuery(sqlbud.toString());
        query.setParameter(1,user_id);
        query.setParameter(2,app_id);
        query.setParameter(3,action_id);
        BigDecimal result = (BigDecimal)query.getSingleResult();
        sumCredit = result == null ? 0L : result.longValue();
        return sumCredit;
    }

    /**
     * 使用分区表查询用户上一天已获取某种积分的总数
     */
    public static Long getCreditByYesterday(Long user_id ,Long app_id ,
                                             Long action_id ){
        Long sumCredit;
        StringBuilder sqlbud = new StringBuilder();
        sqlbud.append("SELECT SUM(CREDIT_NUM) FROM USER_CREDIT_DETAILS ")
                .append(getPartitionByDate(getYesterday()))
                .append(" WHERE USER_ID = ? AND APP_ID = ? AND ACTION_ID = ? AND CREATE_AT BETWEEN TRUNC(SYSDATE) - 1 AND TRUNC(SYSDATE)");
        Query query = JPA.em().createNativeQuery(sqlbud.toString());
        query.setParameter(1,user_id);
        query.setParameter(2,app_id);
        query.setParameter(3,action_id);
        BigDecimal result = (BigDecimal)query.getSingleResult();
        sumCredit = result == null ? 0L : result.longValue();
        return sumCredit;
    }

    /**
     * 使用分区表查询用户当月已获取某种积分的总数
     */
    public static Long getCreditByCurrentMonth(Long user_id ,Long app_id ,
                                             Long action_id ){
        Long sumCredit;
        StringBuilder sqlbud = new StringBuilder();
        sqlbud.append("SELECT SUM(CREDIT_NUM) FROM USER_CREDIT_DETAILS ")
                .append(getPartitionByDate(new Date()))
                .append(" WHERE USER_ID = ? AND APP_ID = ? AND ACTION_ID = ?");
        Query query = JPA.em().createNativeQuery(sqlbud.toString());
        query.setParameter(1,user_id);
        query.setParameter(2,app_id);
        query.setParameter(3,action_id);
        BigDecimal result = (BigDecimal)query.getSingleResult();
        sumCredit = result == null ? 0L : result.longValue();
        return sumCredit;
    }

    /**
     * 使用分区表查询用户当月某几天已获取某种积分的总数
     * 注： 局限于当月，如有特殊情况，请从设计上解决问题，一般只会用到当天查上限
     */
    public static Long getCreditByDate(Long user_id ,Long app_id ,
                                           Long action_id ,Date start,Date end){
        Long sumCredit;
        StringBuilder sqlbud = new StringBuilder();
        sqlbud.append("SELECT SUM(CREDIT_NUM) FROM USER_CREDIT_DETAILS ")
                .append(getPartitionByDate(new Date()))
                .append(" WHERE USER_ID = ? AND APP_ID = ? AND ACTION_ID = ? AND CREATE_AT BETWEEN ? AND ?");
        Query query = JPA.em().createNativeQuery(sqlbud.toString());
        query.setParameter(1,user_id);
        query.setParameter(2,app_id);
        query.setParameter(3,action_id);
        query.setParameter(4,start);
        query.setParameter(5,end);
        BigDecimal result = (BigDecimal)query.getSingleResult();
        sumCredit = result == null ? 0L : result.longValue();
        return sumCredit;
    }

    @Transient
    public static SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

    /**
     * 根据时间返回分区表对应的查询分区字段
     * @param date
     * @return
     */
    public static String getPartitionByDate(Date date){
        StringBuilder partstr = new StringBuilder();
        partstr.append("PARTITION( P")
                .append(format.format(date))
                .append(" ) ");

        return partstr.toString();
    }

    /**
     * 获取昨天的时间
     * @return
     */
    public static Date getYesterday(){
        Date date = new Date();
        long time = date.getTime()-1*60*60*24*1000;
        date.setTime(time);
        return date;
    }
    /**
     * 新增
     */
    public static void userCreditDetailAdd(Long user_id,Long credit_num,
                                           Long app_id,Long action_id,Integer type,
                                           String cause,Long source_id,String source_type,
                                           String note){
        UserCreditDetail ucd = new UserCreditDetail( user_id, credit_num,
                app_id, action_id, type, cause, source_id, source_type, note);
        ucd.save();
    }


}
