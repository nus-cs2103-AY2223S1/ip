import java.time.LocalDateTime;

public class Deadline extends Task {
    private DatesAndTimes time;

    public Deadline(String name, String time) {
        super(name, "D");
        this.time = new DatesAndTimes(time);
    }

    @Override
    public String output() {
        return super.output() + " (by: " + this.time.output() + ")";
    }
}
