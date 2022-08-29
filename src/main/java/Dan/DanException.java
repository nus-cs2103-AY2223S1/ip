package dan;

public class DanException extends Exception {
    public DanException(String message) {
        super("Oh no! We ran into a problem :(\n" + message);
    }
}
