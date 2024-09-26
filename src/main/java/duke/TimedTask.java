package duke;

public class TimedTask extends Task {

    protected String time;

    public TimedTask(String description, String time) {
        super(description);
        this.time = time;
    }

    public TimedTask(String description, String time, boolean done) {
        super(description, done);
        this.time = time;
    }

    public void updateTime(String time) {
        this.time = time;
    }

}
