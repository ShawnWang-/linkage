package modules.credit;

import models.credit.ConfigCreditStrategy;
import models.credit.UserCreditDetail;

/**
 * User: michael
 * Date: 12-12-17
 * Time: 上午3:10
 * Useage:  一次性积分 ，当用户通过此类操作已经获取过积分后，将不能再获取，目前可以限制的时间有限，为一个月内
 * 以免影响效率
 */
public class OnceLimitCreditStrategy implements CalcCreditNumStrategy {
    /**
     * 计算操作产生的积分值
     * @return 积分数值 当不需要记录积分时 返回值为0
     */
    public Long calcCreditNum(Long user_id,ConfigCreditStrategy configCreditStrategy){
        Long alreadyGetCreditNum = UserCreditDetail.getCreditByCurrentMonth(user_id,
                configCreditStrategy.app_id, configCreditStrategy.action_id);
        play.Logger.debug("user: %s this month already get credit num from appid: %s" +
                ", action_id: %s is %s.",
                user_id,configCreditStrategy.app_id,
                configCreditStrategy.action_id,alreadyGetCreditNum);
        //当当天积分已超过当日积分上限时，不再添加
        if (alreadyGetCreditNum >0L){
            return 0L;
        }
        return configCreditStrategy.credit_num;
    }
}