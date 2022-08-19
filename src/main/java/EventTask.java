public class EventTask extends Task {
    private final String duration;
    EventTask(String task, String duration) {
        super(task);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + duration + ")";
    }
}