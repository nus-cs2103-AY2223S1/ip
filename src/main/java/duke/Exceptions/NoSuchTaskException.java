package duke.Exceptions;

public class NoSuchTaskException extends InvalidCommandException {
    public NoSuchTaskException(int numTasks, int index) {
        super(
                String.format("No task found. You only have %d tasks but you referenced a task at index %d",
                        numTasks,
                        index));
    }

    public NoSuchTaskException(int numTasks, String index) {
        super(
                String.format("No task found. You only have %d tasks but you referenced a task at index %s",
                        numTasks,
                        index));
    }
}
