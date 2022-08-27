package duke;

public class Task {
    private final String desc;
    private final char completed;
    private final char taskType;

    public Task(String desc, char taskType) {
        this.desc = desc;
        completed = ' ';
        this.taskType = taskType;
    }

    public Task(String desc, char completed, char taskType) {
        this.desc = desc;
        this.completed = completed;
        this.taskType = taskType;
    }

    public Task(String taskString) {
        int firstBracketIndex = taskString.indexOf('[');
        taskString = taskString.substring(firstBracketIndex);
        taskType = taskString.charAt(1);
        completed = taskString.charAt(4);
        desc = taskString.substring(7);
    }

    protected Task performTask() {
        return new Task(desc, 'X', taskType);
    }

    protected Task undoTask() {
        return new Task(desc, taskType);
    }

    protected String getDesc() {
        return desc;
    }

    protected char getTaskType() {
        return taskType;
    }

    public String toString() {
        return String.format("[%c][%c] %s", taskType, completed, desc);
    }
}
