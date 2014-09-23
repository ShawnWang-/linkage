package modules.credit;

/**
 * User: michael
 * Date: 12-12-14
 * Time: 上午1:09
 * Useage:
 */
public class NotEnoughCreditForConsumerException extends CreditException{
    public NotEnoughCreditForConsumerException(){
        super();
    }

    public NotEnoughCreditForConsumerException(String message){
        super(message);
    }


    public NotEnoughCreditForConsumerException(String message,Throwable cause){
        super(message,cause);
    }

    public NotEnoughCreditForConsumerException(Throwable cause){
        super(cause);
    }
}
