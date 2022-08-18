public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("📆 %s (from %s to %s)", super.toString(), startTime, endTime);
    }
}
