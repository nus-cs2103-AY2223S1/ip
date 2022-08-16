public class JdukeException extends Exception {
    public JdukeException(String message) {
        super(String.format("|  %s", message));
    }
    public JdukeException(String errorType, String suggestion) {
        super(String.format(
           "|  %s%n|    %s",
           errorType, suggestion
        ));
    }
}
