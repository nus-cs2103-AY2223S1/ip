public class DeadlineTask extends Task{
    private String deadline = "";
    public static final String timeMarker = "/by";
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String rawInput) {
        super(rawInput.split("/by")[0]);
        this.deadline = rawInput.split("/by")[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s by %s", super.toString(), this.deadline);
    }
}
