package modules.credit;

/**
 * User: michael
 * Date: 12-11-27
 * Time: 上午11:44
 * Useage: 所有有关积分方面异常的基类
 */
public class CreditException extends Exception {
    public CreditException(){
        super();
    }
    public CreditException(String message){
        super(message);
    }

    public CreditException(String message,Throwable cause){
        super(message,cause);
    }

    public CreditException(Throwable cause){
        super(cause);
    }
}
