public class ChadException extends Exception{
    public ChadException(String message) {
        super("☹ OOPS!!! " + message);
    }

    public String getMessage() {
        return super.toString();
    }
}
