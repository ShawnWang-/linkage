package modules.credit;

import models.credit.ConfigCreditStrategy;
import models.credit.UserCreditDetail;

/**
 * User: michael
 * Date: 12-12-17
 * Time: 上午3:11
 * Useage: 每日限制积分
 */
public class DailyLimitCreditStrategy implements CalcCreditNumStrategy {
    /**
     * 计算操作产生的积分值
     * @return 积分数值 当不需要记录积分时 返回值为0
     */
    public Long calcCreditNum(Long user_id,ConfigCreditStrategy configCreditStrategy){
        Long alreadyGetCreditNum = UserCreditDetail.getCreditByCurrentDay(user_id,
                configCreditStrategy.app_id,configCreditStrategy.action_id);
        play.Logger.debug("user: %s today already get credit num from appid: %s, action_id: %s is %s.",
                user_id,configCreditStrategy.app_id,
                configCreditStrategy.action_id,alreadyGetCreditNum);
        //当当天积分已超过当日积分上限时，不再添加
        if (alreadyGetCreditNum >= configCreditStrategy.credit_limit){
            return 0L;
        }
        return configCreditStrategy.credit_num;
    }
}
