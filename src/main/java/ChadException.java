/**
 * Custom Exception
 */
public class ChadException extends Exception{
    public ChadException(String message) {
        super("☹ OOPS!!! " + message);
    }

    /**
     * Convert exception message into string
     * @return Returns error Message
     */
    public String getMessage() {
        return super.toString();
    }
}
