package duke;

public class DeadlineTask extends TimeTask {
    private final String deadline;
    DeadlineTask(String taskname, String deadline){
        super(taskname, deadline);
        this.deadline = super.toDisplayDate();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    @Override
    String saveFileFormat() {
        return "D###" + super.saveFileFormat();
    }
}
