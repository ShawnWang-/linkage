package modules.credit;

import models.credit.ConfigCreditStrategy;

/**
 * User: michael
 * Date: 12-11-29
 * Time: 下午4:46
 * Useage:
 */
public interface CalcCreditNumStrategy {
    /**
     * 计算操作产生的积分值
     * @return 积分数值 当不需要记录积分时 返回值为0
     */
    public Long calcCreditNum(Long user_id,ConfigCreditStrategy configCreditStrategy);
}
