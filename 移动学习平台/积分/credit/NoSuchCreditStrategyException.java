package modules.credit;

/**
 * User: michael
 * Date: 12-11-28
 * Time: 下午3:04
 * Useage: 但找不到相应积分处理策略的时候，请抛出此异常
 */
public class NoSuchCreditStrategyException extends CreditException {
    public NoSuchCreditStrategyException(){
        super();
    }

    public NoSuchCreditStrategyException(String message){
        super(message);
    }


    public NoSuchCreditStrategyException(String message,Throwable cause){
        super(message,cause);
    }

    public NoSuchCreditStrategyException(Throwable cause){
        super(cause);
    }
}
