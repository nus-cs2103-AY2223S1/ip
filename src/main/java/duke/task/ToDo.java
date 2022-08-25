package duke.task;

public class ToDo extends Task{
    private String taskType;

    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }


    public String getDescription() {
        return super.getDescription();
    }


    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
