package Duke.tasks;

public class Event extends Task {

    private String deadline;
    private String taskDesc;

    public Event(String taskDesc, String deadline) {
        super(taskDesc);
        this.deadline = deadline;
        this.taskDesc = taskDesc;
    }

    @Override
    public char getTaskType() {
        return 'E';
    }

    @Override
    public String getDesc() {
        return this.taskDesc + "|" + this.deadline;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + deadline + ")";
    }
}
