package duke.task;

import duke.Parser;

import java.time.LocalDateTime;

public class EventTask extends Task {

    private final LocalDateTime datetime;
    private final boolean hasTime;

    public EventTask(String name, String date) {
        super(name);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        datetime = LocalDateTime.of(year, month, day, 0, 0);
        hasTime = false;
    }

    public EventTask(String name, String date, String time) {
        super(name);
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4));
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        datetime = LocalDateTime.of(year, month, day, hour, minute);
        hasTime = true;
    }

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
            return String.format("[E][%d] %s | %s %s %d %s%s ", completed, getTaskName(),
                    wordDay, wordMonth, datetime.getYear(),
                    wordHour, wordMinute);
        } else {
            return String.format("[E][%d] %s | %s %s %d", completed, getTaskName(),
                    wordDay, wordMonth, datetime.getYear());
        }
    }
}