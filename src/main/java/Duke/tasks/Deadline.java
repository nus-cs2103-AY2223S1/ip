package Duke.tasks;

public class Deadline extends Task {

    private final String date;
    private String taskDesc;

    public Deadline(String taskDesc, String date) {
        super(taskDesc);
        this.date = date;
        this.taskDesc = taskDesc;
    }

    @Override
    public char getTaskType() {
        return 'D';
    }

    @Override
    public String getDesc() {
        return this.taskDesc + "|" + this.date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
