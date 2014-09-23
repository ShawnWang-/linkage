package modules.credit;

import models.credit.ConfigCreditStrategy;

/**
 * User: michael
 * Date: 12-11-27
 * Time:
 * Useage:   聚合定义积分策略的抽象类
 */
public abstract class CreditStrategy {

    CalcCreditNumStrategy calcCreditNumStrategy; //计算积分时不同规则的积分策略计算方法不一样

    /**
     * 动态的改变积分计算策略
     * @param ccs
     */
    public void setCalcCreditNumStrategy(CalcCreditNumStrategy ccs){
        calcCreditNumStrategy = ccs;
    }

    public  Long getCreditNumResult(Long user_id, ConfigCreditStrategy configCreditStrategy){
        return calcCreditNumStrategy.calcCreditNum(user_id,configCreditStrategy);
    }

    /**
     * 完成用户新增积分时的一系列操作
     * @param user_id
     * @param app_id
     * @param action_id
     */
    abstract Long addUserCredit(Long user_id,Long app_id,Long action_id);

    /**
     * 完成用户消费积分时的一系列操作，参数未完善，
     * @param user_id
     */
    abstract void userConsumeCredit(Long user_id , Long credit_num);

}
