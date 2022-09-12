package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task that is happening at a date, and its duration.
 */
public class Event extends Task {
    protected LocalDate on;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;
    /**
     * Creates an event object upon receiving an event command from the user.
     *
     * @param description The description of the event.
     * @param on The date of the event.
     * @param timeStart The starting time of the event.
     * @param timeEnd The ending time of the event.
     * @param type The type of task created.
     */
    public Event(String description, LocalDate on, LocalTime timeStart, LocalTime timeEnd, TaskType type) {
        super(description, type, Priority.LOW);
        this.on = on;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Creates an event object with manually set priority upon receiving an event command from the user.
     *
     * @param description The description of the event.
     * @param on The date of the event.
     * @param timeStart The starting time of the event.
     * @param timeEnd The ending time of the event.
     * @param type The type of task created.
     * @param priority The priority of the task.
     */
    public Event(String description, LocalDate on, LocalTime timeStart, LocalTime timeEnd,
                 TaskType type, Priority priority) {
        super(description, type, priority);
        this.on = on;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Returns the description of an event with its date and duration for writing purposes.
     *
     * @return The string containing the description.
     */
    @Override
    public String getDescription() {
        return on + " " + timeStart + "-" + timeEnd + " | " + super.getDescription();
    }
    @Override
    public boolean isDateEqual(LocalDate date) {
        return on.isEqual(date);
    }

    @Override
    public String toString() {
        String str = "";
        str += timeStart.format(timeFormatter) + " to ";
        str += timeEnd.format(timeFormatter) + " ";
        str += on.format(dateFormatter);
        return "[E]" + super.toString() + " (on: " + str + ")";
    }
}
