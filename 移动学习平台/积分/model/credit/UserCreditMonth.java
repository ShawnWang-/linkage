package models.credit;

import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * User: michael
 * Date: 12-12-13
 * Time: 上午10:09
 * Useage: 用户积分月结表，用户处理积分到期问题
 * TODO ： 待明确积分过期规则完善该类
 */
@Entity
@Table(name = "user_credits_month")
public class UserCreditMonth extends GenericModel{
    @Id
    @SequenceGenerator(name = "SEQ_USER_CREDIT",
            sequenceName = "SEQ_USER_CREDIT",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER_CREDIT")
    @Column(name="ID" ,nullable = false)
    public Long id;
}
