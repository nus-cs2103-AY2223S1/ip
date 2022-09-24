package hazell.exceptions;

/**
 * Exception when the user chooses an index that does not correspond to any task.
 */
public class NoSuchTask extends HazellException {

    private final int maxNumberOfTasks;
    public NoSuchTask(int maxNumberOfTasks) {
        this.maxNumberOfTasks = maxNumberOfTasks;
    }

    @Override
    public String toString() {
        return String.format(
                "%s There's no such task! Please choose a task from 1 to %d.",
                super.toString(), this.maxNumberOfTasks);
    }
}
