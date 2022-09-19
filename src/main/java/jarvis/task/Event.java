package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that represent an event, have a event date "at"
 */
public class Event extends Task {

    private LocalDateTime at;
    private LocalDateTime end = null;

    /**
     * Construct a event from users' input
     * @param input Include both description and specified time
     * @param isDone Whether the event is done when constructed
     * @throws DateTimeParseException Possible wrong time format when parsing the time
     */
    public Event(String input, boolean isDone) throws DateTimeParseException {
        super(isDone);
        String[] strArr = input.split("/at");
        this.description = strArr[0].trim();
        String[] startNEnd = strArr[1].trim().split("~");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.at = LocalDateTime.parse(startNEnd[0].trim(), formatter);
            if (startNEnd.length > 1) {
                this.end = LocalDateTime.parse(startNEnd[1].trim(), formatter);
            }
        } catch (DateTimeParseException e) {
            //System.out.println("Wrong date format");
            throw e;
        }
    }

    /**
     * Constructor for event from database
     * @param input Description of the event
     * @param times Starting time (and ending time)(if exists) of the event
     * @param isDone The status of the task
     */
    public Event(String input, String times, boolean isDone) {
        super(isDone);
        this.description = input;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] startNEnd = times.split("~");
        try {
            this.at = LocalDateTime.parse(startNEnd[0].trim());
            if (startNEnd.length > 1) {
                this.end = LocalDateTime.parse(startNEnd[1].trim());
            }
        } catch (DateTimeParseException e) {
            //System.out.println("Wrong date format");
            throw e;
        }
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Event;
    }

    /**
     * For storing event to data file
     * @return The string representation of this event in data file
     */
    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        String endTime = this.end == null
                ? ""
                : "~" + this.end;
        return "E|" + done + "|" + this.description + "|" + this.at + endTime + "\n";
    }

    @Override
    public String toString() {
        String start = this.at.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
        String head = "[E][" + this.getStatusIcon() + "] ";
        String body;
        if (this.end == null) {
            body = this.description + " (at: " + start + ")";
        } else {
            String end = this.end.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
            body = this.description + " (from: " + start + " to: " + end + ")";
        }
        return head + body;
    }

    /**
     * Compare this event with other task
     * Events has higher priority than todos(-1) and lower than deadlines(1)
     * Compare among events by their time. Earlier time has higher priority
     * @param task2 the other task to be compared with
     * @return The priority -1, 0 or 1 as specified above
     */
    @Override
    public int compareTo(Task task2) {
        if (task2 instanceof Todo) {
            return -1;
        } else if (task2 instanceof Deadline) {
            return 1;
        }
        Event event = (Event) task2;
        return this.at.compareTo(event.at);
    }
}
