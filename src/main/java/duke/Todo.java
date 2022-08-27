package duke;

public class Todo extends Task{
    public Todo(String desc, char taskType) {
        super(desc, taskType);
    }

    public Todo(String desc, char completed, char taskType) {
        super(desc, completed, taskType);
    }

    public Todo(String desc) {
        super(desc);
    }
    protected Todo performTask() {
        return new Todo(this.getDesc(), 'X', this.getTaskType());
    }

    protected Todo undoTask() {
        return new Todo(this.getDesc(), this.getTaskType());
    }
}
