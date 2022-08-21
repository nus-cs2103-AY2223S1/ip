public class Deadline extends Task {
    String time;

    public Deadline (TaskType type, String name, String time) {
        super(type, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by:" + time + ")";
    }
}
