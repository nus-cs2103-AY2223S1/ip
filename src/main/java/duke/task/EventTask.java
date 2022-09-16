package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Represents a task that will occur at a certain time.
 */
public class EventTask extends Task {

    private final LocalDateTime datetime;
    private final boolean hasTime;

    /**
     * Creates a new task that will occur at a certain time.
     *
     * @param description Task that needs to be done.
     * @param date Date on which task will occur.
     */
    public EventTask(String description, String date) {
        super(description);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        datetime = LocalDateTime.of(year, month, day, 0, 0);
        hasTime = false;
    }

    /**
     * Creates a new task that will occur at a certain time.
     *
     * @param description Task that needs to be done.
     * @param date Date on which task will occur.
     * @param time Time at which task will occur.
     */
    public EventTask(String description, String date, String time) {
        super(description);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        datetime = LocalDateTime.of(year, month, day, hour, minute);
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
        String wordMonth = Parser.intToWordMonth(datetime.getMonth().getValue());
        String wordDay = String.valueOf(datetime.getDayOfMonth());
        if (datetime.getDayOfMonth() < 10) {
            wordDay = "0" + wordDay;
        }
        String wordMinute = String.valueOf(datetime.getMinute());
        if (datetime.getMinute() < 10) {
            wordMinute = "0" + wordMinute;
        }
        String wordHour = String.valueOf(datetime.getHour());
        if (datetime.getHour() < 10) {
            wordHour = "0" + wordHour;
        }
        if (hasTime) {
            return String.format("[E][%d] %s | %s %s %d %s%s", completed, getTaskDescription(),
                    wordDay, wordMonth, datetime.getYear(),
                    wordHour, wordMinute);
        } else {
            return String.format("[E][%d] %s | %s %s %d", completed, getTaskDescription(),
                    wordDay, wordMonth, datetime.getYear());
        }
    }

}
