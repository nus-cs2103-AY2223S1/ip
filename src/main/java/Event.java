import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalDate at;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, String dateAndTime) {
        super(description);
        String[] array1 = dateAndTime.split(" from ");
        String[] array2 = array1[1].split(" to ");
        this.at = LocalDate.parse(array1[0]);
        this.startTime = LocalTime.parse(array2[0]);
        this.endTime = LocalTime.parse(array2[1]);
    }

    public Event(String description, LocalDate at, LocalTime startTime, LocalTime endTime, boolean isDone) {
        super(description);
        this.at = at;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String date = at.getDayOfMonth() + " " + at.getMonth().toString() + " " + at.getYear() + " "
                + startTime.toString() + " to " + endTime.toString();
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}