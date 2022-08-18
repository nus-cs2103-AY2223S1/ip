package exceptions;

public class NoSuchTask extends HazellException {

    private int maxNumberOfTasks;
    public NoSuchTask(int maxNumberOfTasks) {
        this.maxNumberOfTasks = maxNumberOfTasks;
    }

    @Override
    public String toString() {
        return String.format(
                "☹ OOPS!!! There's no such task! Please choose a task from 1 to %d.",
                this.maxNumberOfTasks);
    }
}
