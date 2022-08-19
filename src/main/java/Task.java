import java.util.Arrays;

public abstract class Task {
    private String taskType;
    private String description;
    private boolean isDone;
    private String[] command;

    public Task(String[] command, String taskType, boolean isDone, String[] args) throws DukeException {
        this.command = command;
        this.taskType = taskType;
        this.isDone = isDone;
        this.description = Arrays.stream(args).skip(1).reduce("", (x, y) -> x + " " + y);
        if (this.description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + this.taskType + " cannot be empty.");
        }
    }

    public String[] getCommand() {
        return command;
    }

    public char getStatusIcon() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    public char getTaskTypeIcon() {
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
