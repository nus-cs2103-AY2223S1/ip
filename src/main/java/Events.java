public class Events extends Task {

    private String taskName;
    private String time;
    private boolean isDone = false;

    public Events(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at:" + time + ")";
    }
}
