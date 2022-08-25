public class WanyaException extends Exception{
    public WanyaException(String message) {
        super("Whoopsie!!! " + message);
    }
}
