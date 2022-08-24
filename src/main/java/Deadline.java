public class Deadline extends Task {
    // original access modifier was protected
    private final String by;

    public Deadline(String description, Command taskCommand, String by) {
        super(description, taskCommand);
        this.by = by;
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommand.getString() + " " + description + " /by " + by + "\n" + getTaskDoneString(index);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
