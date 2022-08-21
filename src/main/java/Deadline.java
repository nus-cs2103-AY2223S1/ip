
public class Deadline extends Task{

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String TaskInfo() {
        return "[D] [" + getStatusIcon() + "] " + description + "(by:" + deadline +")";
    }

    @Override
    public String TaskSaveInfo() {
        return "D," + getSavedStatusIcon() + "," + description + "," + deadline;
    }
}
