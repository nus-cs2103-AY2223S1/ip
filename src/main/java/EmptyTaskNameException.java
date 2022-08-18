public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException(String taskType) {
        super("You can't just say " + taskType + " without any description.");
    }
}
