package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private String time;

    /**
     * A constructor to initialize the event.
     *
     * @param isDone A boolean to indicate if the event is done.
     * @param eventDescription A string to detail the event.
     * @param index The task number in the list of tasks to do.
     * @param tag A string to describe event in one word.
     * @param date A LocalDate to detail date of event.
     * @param time A string to detail time of event.
     */
    public Event(boolean isDone, String eventDescription, int index, String tag, LocalDate date, String time) {
        super(isDone, eventDescription, index, tag);
        this.date = date;
        this.time = time;
    }

    /**
     * Changes the Event from undone to done.
     *
     * @return a String representation on details of the Event done.
     */
    public String markDone() {
        return super.markDone();
    }

    /**
     * Changes the Event from done to undone.
     *
     * @return a String representation on details of the Event undone.
     */
    public String markUndone() {
        return super.markUndone();
    }

    /**
     * Updates the index of the task to reflect the task's position in
     * the list of tasks.
     *
     * @param newIndex The current index of the event in the list of tasks.
     */
    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    /**
     * Returns a string describing the Event added to the list of tasks.
     *
     * @return a String representation of Event added.
     */
    @Override
    public String printAdded() {
        return "\n  Looks like you have a new event:\n    [E][ ] " + this.getDescription()
                + " (at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")"
                + "\n  " + this.getIndex() + " tasks left, ganbare!\n";
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return a String describing the Event.
     */
    @Override
    public String printTask() {
        if (!this.getStatus()) {
            return "  " + this.getIndex() + ".[E][ ] " + this.getDescription() + " (at: "
                    + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
        } else {
            return "  " + this.getIndex() + ".[E][X] " + this.getDescription() + " (at: "
                    + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
        }
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return a String describing the Event.
     */
    @Override
    public String toString() {
        if (!this.getStatus()) {
            return this.getIndex() + ".[E][ ] " + this.getDescription() + " | at: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " | " + time;
        } else {
            return this.getIndex() + ".[E][X] " + this.getDescription() + " | at: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))  + " | " + time;
        }
    }

    /**
     * Returns a string to be saved in the hard disk.
     *
     * @return A string representation of event to be saved in the hard disk.
     */
    @Override
    public String toSavedString() {
        if (!this.getStatus()) {
            return "EN<" + this.getDescription() + ">" + "(" +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")" + "{" + time + "}" + "/n";
        } else {
            return "EY<" + this.getDescription() + ">" + "(" +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")" + "{" + time + "}" + "/n";
        }
    }

    /**
     * Returns a string describing the task deleted from the list of tasks.
     *
     * @return a String representation of the task deleted.
     */
    @Override
    public String printDeleted() {
        if (!this.getStatus()) {
            return "\n  duke.Task deleted!\n    [E][ ] " + this.getDescription()
                    + "(at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
        } else {
            return "\n  duke.Task deleted!\n    [E][X] " + this.getDescription()
                    + "(at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
        }
    }

    @Override
    public String tag(String hashtag) {
        return super.tag(hashtag);
    }

}
