package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int day, month, year, hours, minutes;

    /**
     * Constructor to initialise a Task object.
     *
     * @param description Task's outline.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getDate();

    /**
     * Returns [X] if task is done. Else, [ ].
     *
     * @return String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Set isDone to bool.
     *
     * @param bool Boolean.
     */
    public void toggleIsDone(boolean bool) {
        isDone = bool;
    }

    /**
     * Returns [X] if task is done. Else, [ ].
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a LocalDateTime object.
     *
     * @param date String of the date with format dd/mm/yyyy 0000.
     * @return LocalDateTime object.
     * @throws DukeException
     */
    public LocalDateTime getLocalDateTime(String date, String taskType, String filler) throws DukeException {
        if (checkValidDate(date)) {
            return LocalDateTime.of(year, month, day, hours, minutes);
        } else {

            throw new DukeException("OOPS! Format must be: " + taskType
                    + " <description> " + filler + " <dd/mm/yyyy> <time> with time being 24hours.");
        }
    }

    /**
     * Check for String format and validity of date.
     *
     * @param date String of the date with format dd/mm/yyyy 0000.
     * @return boolean. True if is a valid date.
     * @throws DukeException if invalid date.
     */
    public boolean checkValidDate(String date) throws DukeException {
        if (date.length() == 15 && date.substring(2, 3).equals("/") && date.substring(5, 6).equals("/")) {
            this.day = Integer.parseInt(date.substring(0, 2));
            this.month = Integer.parseInt(date.substring(3, 5));
            this.year = Integer.parseInt(date.substring(6, 10));
            this.hours = Integer.parseInt(date.substring(11, 13));
            this.minutes = Integer.parseInt(date.substring(13, 15));
            return day > 0 && day <= 31 && month <= 12 && month > 0
                    && year > 0 && hours <= 23 && hours >= 0 && minutes >= 0 && minutes <= 59;
        } else {
            throw new DukeException("OOPS! Format must be <dd/mm/yyyy> <time> with time being 24hours.");
        }
    }
}
