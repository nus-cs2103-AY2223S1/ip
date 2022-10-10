package duke.task;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an Event task with an event description, start date and end date. This class inherits Task class.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * This method is an Event Task constructor.
     * An event Task contains a task description, a task status, tags, event start date and end date.
     * @param description a task description string.
     * @param startDate a LocalDate object of event start date.
     * @param endDate a LocalDate object of event end date.
     *
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * This method is an overloaded Event Task constructor.
     * @param description a task description string.
     * @param isDone event task status (false: not done. true: done).
     * @param tags a list of tags.
     * @param startDate a LocalDate object of event start date.
     * @param endDate a LocalDate object of event end date.
     */
    public Event(String description, Boolean isDone, LocalDate startDate, LocalDate endDate, List<String> tags) {
        super(description, isDone, tags);
        assert startDate.isBefore(endDate) || startDate.isEqual(endDate)
                : "invalid date range (start date after end date).";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns Event task status icon. This method overrides the method from its super class Task.
     * In the status icon, [E] represents task type Event, [X] represents task status as done,
     * [ ] represents task status as not done.
     * @return String representation of Event task status icon.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][X]" : "[E][ ]"); // mark done task with X
    }

    /**
     * Returns a string of all event information message, including event description, start date and end date.
     * This method overrides the method from its super class Task.
     * @return a string of all event information message.
     */
    @Override
    public String getDescription() {
        return String.format("%s (at %s - %s)", this.description, this.startDate, this.endDate);
    }

    /**
     * Returns a string format of Event task. The format is | Event | status | description | time | tags
     * This method is used to convert an Event task to string and store it in storage.
     * @return string format of Event task.
     */
    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Event     | %s | %s | %s to %s | %s", status, super.getDescription(),
                this.startDate, this.endDate, super.printTags());
    }

    /**
     * Returns the task type of Event task. This method overrides the method from its super class Task.
     * @return string "event".
     */
    @Override
    public String taskType() {
        return "event";
    }

    /**
     * Returns the start date of Event task.
     * @return LocalDate object startDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of Event task.
     * @return LocalDate object endDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}
