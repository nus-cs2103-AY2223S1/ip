import java.util.Arrays;

public abstract class Task {
    private String taskType;
    protected String description;
    protected boolean isDone;

    public Task(String taskType, String[] args) throws DukeException {
        this.taskType = taskType;
        this.description = Arrays.stream(args).skip(1).reduce("", (x, y) -> x + " " + y);
        this.isDone = false;
        if (this.description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + this.taskType + " cannot be empty.");
        }
    }

    private char getStatusIcon() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    private char getTaskTypeIcon() {
        return taskType.toUpperCase().charAt(0);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description;
    }
}
