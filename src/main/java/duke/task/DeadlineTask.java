package duke.task;

import duke.Parser;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    private final LocalDateTime deadline;
    private final boolean hasTime;

    public DeadlineTask(String name, String date) {
        super(name);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        deadline = LocalDateTime.of(year, month, day, 0, 0);
        hasTime = false;
    }

    public DeadlineTask(String name, String date, String time) {
        super(name);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        deadline = LocalDateTime.of(year, month, day, hour, minute);
        hasTime = true;
    }

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
            return String.format("[D][%d] %s | %s %s %d %s%s", completed, getTaskName(),
                    wordDay, wordMonth, deadline.getYear(),
                    wordHour, wordMinute);
        } else {
            return String.format("[D][%d] %s | %s %s %d", completed, getTaskName(),
                    wordDay, wordMonth, deadline.getYear());
        }

    }
}