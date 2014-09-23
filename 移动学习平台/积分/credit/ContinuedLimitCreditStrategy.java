package modules.credit;

import models.credit.ConfigCreditStrategy;
import models.credit.UserCreditDetail;

import java.util.Date;

/**
 * User: michael
 * Date: 12-12-17
 * Time: 上午3:13
 * Useage:   连续登陆签到类积分，此类每日只能获取一次，连续登录累计，有最高上限
 *
 */
public class ContinuedLimitCreditStrategy implements CalcCreditNumStrategy {
    /**
     * 计算操作产生的积分值
     * @return 积分数值 当不需要记录积分时 返回值为0
     */
    public Long calcCreditNum(Long user_id,ConfigCreditStrategy configCreditStrategy){
        Long todayGetCreditNum = UserCreditDetail.getCreditByCurrentDay(user_id,
                configCreditStrategy.app_id, configCreditStrategy.action_id);
        Long yesterdayGetCreditNum = UserCreditDetail.getCreditByYesterday(user_id,
                configCreditStrategy.app_id, configCreditStrategy.action_id);
        play.Logger.debug("user: %s yesterday,today already get credit num from appid: %s," +
                " action_id: %s is %s,%s.",
                user_id,configCreditStrategy.app_id,
                configCreditStrategy.action_id,yesterdayGetCreditNum,todayGetCreditNum);
        //当当天以获取积分时，不再添加，注连续积分诸如签到，每日只能获取一次
        if (todayGetCreditNum > 0L){
            return 0L;
        }
        //当昨天没有获取积分，或者昨天积分已达到连续上限，重新开始一个周期
        if (yesterdayGetCreditNum == 0L ||
                yesterdayGetCreditNum >= configCreditStrategy.credit_limit){
            return configCreditStrategy.credit_num;
        }
        //如果配置不正确的时候视为普通积分处理
        if (configCreditStrategy.credit_continued == null){
            play.Logger.error("cfg_app_action_credit_strategy 策略配置有问题，没有配置连续加权系数！！！");
            return configCreditStrategy.credit_num;
        }
        //如果连续登录，将昨天的积分加权连续系数
        return yesterdayGetCreditNum+configCreditStrategy.credit_continued;



    }
}
