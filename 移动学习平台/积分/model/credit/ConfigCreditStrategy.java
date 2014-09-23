package models.credit;


import play.cache.*;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import javax.persistence.Cache;
import java.util.Date;

/**
 * User: michael
 * Date: 12-12-13
 * Time: 上午10:12
 * Useage:
 */
@Entity
@Table(name = "cfg_app_action_credit_strategy")
public class ConfigCreditStrategy extends GenericModel{
    @Id
    @SequenceGenerator(name = "SEQ_USER_CREDIT",
            sequenceName = "SEQ_USER_CREDIT",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER_CREDIT")
    @Column(name="ID",nullable = false)
    public Long id;
    @Column(name="",nullable = false)
    public String name;
    @Column(name="app_id",nullable = false)
    public Long app_id;
    @Column(name="app_name",nullable = false)
    public String app_name;
    @Column(name="action_id",nullable = false)
    public Long action_id;
    @Column(name="action_name",nullable = false)
    public String action_name;
    @Column(name="CREDIT_NUM",nullable = false)
    public Long credit_num;        //单次操作积分值
    @Column(name="CREDIT_STRATEGY",nullable = false)
    public String credit_strategy;  //积分策略定制
    @Column(name="CREDIT_LIMIT",nullable = false)
    public Long credit_limit;      //积分限制，根据积分策略不同，为对应的不同限制值
    @Column(name="CREDIT_MULTIPLY")
    public Long credit_multply;       //多倍积分，应用于活动或多倍积分策略
    @Column(name="CREDIT_CONTINUED")
    public Long credit_continued;    //用于连续签到的策略，最多连续多少天会有加权
    @Column(name="CREDIT_START")
    public Date credit_start;    //辅助字段 特殊积分策略 有效时段 ，例如 多倍
    @Column(name="CREDIT_END")
    public Date credit_end;    //辅助字段 特殊积分策略 有效时段 ，例如 多倍
    @Column(name="CREATE_BY")
    public Long create_by;
    @Column(name="CREATE_AT")
    public Date create_at;
    @Column(name="UPDATE_BY")
    public Long update_by;
    @Column(name="UPDATE_AT")
    public Date update_at;
    @Column(name="USER_TYPE")
    public Long user_type;

    /**
     * 获取系统配置的积分策略
     * @param app_id
     * @param action_id
     * @return
     */
    public static ConfigCreditStrategy getConfigCreditStrategy(Long app_id,Long action_id,
                                                               Long user_type){
        ConfigCreditStrategy ccs = (ConfigCreditStrategy)
                play.cache.Cache.get("CREDITSTRATEGY_" + app_id
                        + "_" + action_id + "_" + user_type);
        if( null == ccs){
        //TODO 这里有一个问题，如果确实查不到，那不每次都要查数据库，有没有什么好方法解决
            ccs = ConfigCreditStrategy.getByAppIdActionIdUserType(app_id,action_id,user_type);
            if (ccs != null){
                play.cache.Cache.set(
                        "CREDITSTRATEGY_" + app_id + "_" + action_id + "_" + user_type,
                        ccs,"30mn");//页面或数据库策略修改完之后30分钟生效
            }
        }
        return ccs;
    }


    /**
     *
     * @param app_id
     * @param action_id
     * @param user_type 固定type的优先级大于默认，优先获取当前用户类型的，获取不到使用默认用户策略
     * @return
     */
    public static ConfigCreditStrategy getByAppIdActionIdUserType(
            Long app_id,Long action_id,Long user_type){
        ConfigCreditStrategy ccs = find(
                "app_id = ? and action_id = ? and " +
                        "(user_type = ? or user_type = 0) " +
                        "order by user_type desc",
                app_id,action_id,user_type).first();
        return ccs;
    }

}
