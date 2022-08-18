public class EmptyTimingException extends Exception {
    public EmptyTimingException(String taskType) {
        super("You have not set a timing for your " + taskType + " buddy.");
    }
}
