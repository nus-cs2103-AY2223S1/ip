package exception;

/**
 * Represents exceptions specific to Doris bot
 *
 * @author Marcus Low
 */
public class DorisException extends Exception {
    public DorisException(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return getMessage();
    }
}
