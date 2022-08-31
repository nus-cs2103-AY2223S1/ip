public class EmptyTimingException extends Exception {
    public EmptyTimingException(CommandType taskType) {
        super("You have not set a timing for your " + taskType + " buddy.");
    }
}
