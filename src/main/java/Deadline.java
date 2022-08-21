public class Deadline extends Task {
    String time;

    public Deadline (TaskType type, String name, boolean marked, String time) {
        super(type, name, marked);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by:" + time + ")";
    }
}
