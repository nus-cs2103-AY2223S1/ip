public class InvalidFormattingException extends Exception {
    public InvalidFormattingException(String taskType) {
        super("Wrong formatting for a " + taskType + " bro.");
    }
}
