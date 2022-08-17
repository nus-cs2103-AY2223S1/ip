public class EventTask extends Task{
    private String period = "";
    public static final String timeMarker = "/at";
    public EventTask(String description, String period) {
        super(description);
        this.period = period;
    }

    public EventTask(String rawInput) {
        super(rawInput.split("/at")[0]);
        this.period = rawInput.split("/at")[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s at %s", super.toString(), this.period);
    }
}
