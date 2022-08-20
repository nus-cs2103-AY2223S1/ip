import java.time.LocalDate;

public class Event extends Task{

    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String name, boolean done, String startTime, String endTime) throws TaskNoNameException {
        super(name, done);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from "
                + DukeDateTimeFormatter.format(this.startTime) + " to "
                + DukeDateTimeFormatter.format(this.endTime) + ")";
    }

}