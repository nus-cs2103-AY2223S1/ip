public class InvalidFormattingException extends Exception {
    public InvalidFormattingException(CommandType taskType) {
        super("Wrong formatting for a " + taskType + " bro.");
    }
}
