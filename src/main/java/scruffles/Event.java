package scruffles;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An event is a task that occurs at a specific timing on a certain date
 *
 * @author Shamus Tan
 */
public class Event extends Task {

    protected LocalDate at;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructor for Event
     *
     * @param description the name of the Event task
     * @param dateAndTime the associated date and timings of the event as a string
     * @throws TimeErrorException when the end time is before the start time
     */
    public Event(String description, String dateAndTime) throws TimeErrorException {
        super(description);
        String[] array1 = dateAndTime.split(" from ");
        String[] array2 = array1[1].split(" to ");
        this.at = LocalDate.parse(array1[0]);
        this.startTime = LocalTime.parse(array2[0]);
        this.endTime = LocalTime.parse(array2[1]);
        if (startTime.isAfter(endTime)) {
            throw new TimeErrorException("grrrr >:( end time must be after start time woof woof!");
        }
    }

    /**
     * Constructor for Event
     *
     * @param description the name of the Event task
     * @param at the date of the Event
     * @param startTime the starting time of the Event
     * @param endTime the ending time of the Event
     * @param isDone whether the task has been done
     */
    public Event(String description, LocalDate at, LocalTime startTime, LocalTime endTime, boolean isDone) {
        super(description);
        this.at = at;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
        assert startTime.isBefore(endTime) : "start time should be before end time";
    }

    @Override
    public String toString() {
        String date = at.getDayOfMonth() + " " + at.getMonth().toString() + " " + at.getYear() + " "
                + startTime.toString() + " to " + endTime.toString();
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
