package modules.credit;

import models.credit.ConfigCreditStrategy;
import models.credit.UserCreditDetail;

import java.util.Date;

/**
 * User: michael
 * Date: 12-12-17
 * Time: 上午3:16
 * Useage:     多倍积分策略   ,在某一个活动期间内多倍算，当日总积分值不超过上限
 */
public class MultipleCreditStrategy  implements  CalcCreditNumStrategy{
    /**
     * 计算操作产生的积分值
     * @return 积分数值 当不需要记录积分时 返回值为0
     */
    public Long calcCreditNum(Long user_id,ConfigCreditStrategy configCreditStrategy){
        Long alreadyGetCreditNum = UserCreditDetail.getCreditByCurrentDay(user_id,
                configCreditStrategy.app_id, configCreditStrategy.action_id);
        play.Logger.debug("user: %s today already get credit num from appid: %s, action_id: %s is %s.",
                user_id,configCreditStrategy.app_id,
                configCreditStrategy.action_id,alreadyGetCreditNum);
        //当当天积分已超过当日积分上限时，不再添加
        if (alreadyGetCreditNum >= configCreditStrategy.credit_limit){
            return 0L;
        }
        if (configCreditStrategy.credit_multply == null){
            play.Logger.error("cfg_app_action_credit_strategy 策略配置有问题，没有配置连续多倍系数！！！");
            return configCreditStrategy.credit_num;
        }
        Date curdate = new Date();
        //判断是否在活动期间
        if(configCreditStrategy.credit_start!=null && configCreditStrategy.credit_end !=null &&
                curdate.after(configCreditStrategy.credit_start)
                &&curdate.before(configCreditStrategy.credit_end)){
            return configCreditStrategy.credit_num*configCreditStrategy.credit_multply;
        }
        //如果不在时间之间，按照普通积分处理
        return configCreditStrategy.credit_num;
    }
}
