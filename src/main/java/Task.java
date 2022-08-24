public class Task {
    private final String desc;
    private final char completed;
    private final char taskType;

    public Task(String desc, char taskType) {
        this.desc = desc;
        this.completed = ' ';
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
        this.taskType = taskString.charAt(1);
        this.completed = taskString.charAt(4);
        this.desc = taskString.substring(7);
    }

    protected Task performTask() {
        return new Task(this.desc, 'X', this.taskType);
    }

    protected Task undoTask() {
        return new Task(this.desc, this.taskType);
    }

    protected String getDesc() {
        return desc;
    }

    protected char getTaskType() {
        return taskType;
    }

    public String toString() {
        return String.format("[%c][%c] %s", this.taskType, this.completed, this.desc);
    }
}
