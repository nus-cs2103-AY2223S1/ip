package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Represents a task that is to be completed by a deadline.
 */

public class DeadlineTask extends Task {

    private final LocalDateTime deadline;
    private final boolean hasTime;

    /**
     * Creates a new task with a description and a deadline.
     *
     * @param description Task that needs to be done.
     * @param date Date which task should be done by.
     */
    public DeadlineTask(String description, String date) {
        super(description);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        deadline = LocalDateTime.of(year, month, day, 0, 0);
        hasTime = false;
    }

    /**
     * Creates a new task with a description and a deadline.
     *
     * @param description Task that needs to be done.
     * @param date Date which task should be done by, in ddmmyyyy format.
     * @param time Time which task should be done by in 24-hour format.
     */
    public DeadlineTask(String description, String date, String time) {
        super(description);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        deadline = LocalDateTime.of(year, month, day, hour, minute);
        hasTime = true;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        int completed = 0;
        if (isCompleted()) {
            completed = 1;
        }
        String wordMonth = Parser.intToWordMonth(deadline.getMonth().getValue());
        String wordDay = String.valueOf(deadline.getDayOfMonth());
        if (deadline.getDayOfMonth() < 10) {
            wordDay = "0" + wordDay;
        }
        String wordMinute = String.valueOf(deadline.getMinute());
        if (deadline.getMinute() < 10) {
            wordMinute = "0" + wordMinute;
        }
        String wordHour = String.valueOf(deadline.getHour());
        if (deadline.getHour() < 10) {
            wordHour = "0" + wordHour;
        }
        if (hasTime) {
            return String.format("[D][%d] %s | %s %s %d %s%s", completed, getTaskDescription(),
                    wordDay, wordMonth, deadline.getYear(),
                    wordHour, wordMinute);
        } else {
            return String.format("[D][%d] %s | %s %s %d", completed, getTaskDescription(),
                    wordDay, wordMonth, deadline.getYear());
        }
    }

}
