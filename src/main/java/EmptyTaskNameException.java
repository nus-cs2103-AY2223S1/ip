public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException(CommandType taskType) {
        super("You can't just say " + taskType + " without any description.");
    }
}
