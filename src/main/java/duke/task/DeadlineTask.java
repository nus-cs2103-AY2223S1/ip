package duke.task;

public class DeadlineTask extends TimeTask {
    private final String deadline;
    public DeadlineTask(String taskname, String deadline){
        super(taskname, deadline);
        this.deadline = super.toDisplayDate();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    @Override
    public String saveFileFormat() {
        return "D###" + super.saveFileFormat();
    }
}
