package modules.credit;

import models.credit.ConfigCreditStrategy;
import models.credit.UserCredit;
import models.credit.UserCreditDetail;
import models.publics.PublicUsers;
import play.cache.Cache;

import java.util.HashMap;

/**
 * User: michael
 * Date: 12-12-17
 * Time: 上午2:23
 * Useage: 积分操作实现类
 */
public class Credit extends CreditStrategy {

    /**
     * 完成用户新增积分时的一系列操作
     * @param user_id
     * @param app_id
     * @param action_id
     * @return 0L 时 代表积分创建失败
     */
    public Long addUserCredit(Long user_id,Long app_id,Long action_id){
        play.Logger.info("add user credit userid: %s ,app_id: %s ,action: %s",
                user_id,app_id,action_id);
        PublicUsers credituser = PublicUsers.findById(user_id);
        if (credituser == null){
            return  0L;
        }
        ConfigCreditStrategy configCreditStrategy = ConfigCreditStrategy.
                getConfigCreditStrategy(app_id, action_id,credituser.type);

        //当对应的 appid actionid 没有积分策略，或者不产生积分时
        if (configCreditStrategy == null || configCreditStrategy.credit_num == 0L){
            return 0L;
        }
        play.Logger.info("credit strategy is [%s]!",configCreditStrategy.credit_strategy);
        //根据配置获取积分计算策略
        this.calcCreditNumStrategy = CalcCreditNumStrategyFactory
                .createCalcCreditNumStrategy(configCreditStrategy.credit_strategy);
        //当积分策略不存在时
        if (this.calcCreditNumStrategy == null){
            return 0L;
        }

        Long credit_num = this.getCreditNumResult(user_id,configCreditStrategy);
        if (credit_num == 0L){
            return 0L;
        }
        play.Logger.info("credit num is [%s]!",credit_num);
        //更新用户积分总表
        play.Logger.info("add credit for userid : [%s],credit_num: [%s]!",user_id,credit_num);
        UserCredit.userCreditAdd(user_id,credit_num);
        //更新用户积分明细表
        UserCreditDetail.userCreditDetailAdd(user_id,credit_num,app_id,action_id,
                0,configCreditStrategy.name,null,null,null);
        return credit_num;
    }

    /**
     * 完成用户消费积分时的一系列操作，参数未完善，
     * @param user_id
     */
    public void userConsumeCredit(Long user_id , Long credit_num){

    }



    /**
     * 积分计算策略工厂类
     */
    public static class CalcCreditNumStrategyFactory{
        //初始化积分策略，不需要每次都new 一个
        private static HashMap<String,CalcCreditNumStrategy> creditStrategysMap
                = new HashMap<String,CalcCreditNumStrategy>();

        //根据策略名返回策略类
        public static CalcCreditNumStrategy createCalcCreditNumStrategy(String calcCreditNumStrategyName){
            CalcCreditNumStrategy calcCreditNumStrategy = null;
            try {
                if(creditStrategysMap.containsKey(calcCreditNumStrategyName)){
                    calcCreditNumStrategy = creditStrategysMap.get(calcCreditNumStrategyName);
                }else {
                    calcCreditNumStrategy = (CalcCreditNumStrategy)Class
                            .forName(calcCreditNumStrategyName).newInstance();
                    creditStrategysMap.put(calcCreditNumStrategyName,calcCreditNumStrategy);
                }
            } catch (Exception e){
                return calcCreditNumStrategy;
            }
            return calcCreditNumStrategy;
        }

    }
}
