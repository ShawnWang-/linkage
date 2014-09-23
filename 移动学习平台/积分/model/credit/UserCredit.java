package models.credit;

import modules.credit.NotEnoughCreditForConsumerException;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

/**
 * User: michael
 * Date: 12-12-13
 * Time: 上午10:08
 * Useage:
 */
@Entity
@Table(name="user_credits")
public class UserCredit extends GenericModel{
    @Id
    @SequenceGenerator(name = "SEQ_USER_CREDIT",
            sequenceName = "SEQ_USER_CREDIT",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER_CREDIT")
    @Column(name="ID" ,nullable = false)
    public Long id;

    @Column(name="user_id", nullable = false ,unique = true)
    public Long user_id;

    @Column(name="credit_usable", nullable = false)
    public Long credit_usable;    //用户当前可用积分

    @Column(name="credit_consume" )
    public Long credit_consume;  //用户消费积分数目，用于结算月积分，结算之后置为0

    @Column(name="credit_become_due")
    public Long credit_become_due;   //即将到期积分（提示即将到期的月份，每月初月结一次），由后台任务更新

    @Column(name="create_at")
    public Date create_at;

    @Column(name="update_at")
    public Date update_at;

    /**
     * 计算当月截止月底即将到期的积分总数
     * @param user_id 用户id
     * #TODO 系统暂时不支持积分过期，如需此功能请通过积分月结来实现
     */
    public Long calcCreditBecomeDueByEndMonth(Long user_id){
        long result = 0;
        return result;
    }

    /**
     * 为用户增加 credit_num 积分
     * @param credit_num  追加积分数目
     */
    public static void userCreditAdd(Long user_id, Long credit_num){
        UserCredit uct = findByUserID(user_id);
        play.Logger.debug("userCreditAdd userid %s,credit_num is %s",user_id,credit_num);
        if(uct == null){
            uct = new UserCredit();
            uct.user_id = user_id;
            uct.credit_usable = credit_num;
            uct.credit_become_due = 0L;
            uct.credit_consume = 0L;
            uct.update_at = new Date();
            uct.create_at = new Date();
            uct.save();
        }else {
            uct.credit_usable = uct.credit_usable + credit_num;
            uct.update_at = new Date();
            uct.save();
        }

    }

    /**
     * 消费积分
     * @param user_id
     * @param credit_num        积分消费数目
     * @throws NotEnoughCreditForConsumerException     当用户积分不足时，抛出此异常
     */
    public static void userCreditConsume(Long user_id, Long credit_num) throws NotEnoughCreditForConsumerException{
        UserCredit uct = findByUserID(user_id);
        if(uct == null || uct.credit_usable < credit_num) {
            throw new NotEnoughCreditForConsumerException("there is not enough credits for consume! ");
        }
        uct.credit_usable -=  credit_num;
        uct.credit_consume += credit_num;
        uct.credit_become_due -= credit_num; //当用户消费后，会及时更新即将到期积分数，如到期积分完全消费，置于0
        if (uct.credit_become_due < 0L)
            uct.credit_become_due = 0L;
        uct.update_at = new Date();
        uct.save();
    }

    public static UserCredit findByUserID(Long user_id ){
        return find(" user_id = ? ", user_id).first();
    }


}
