import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;

import java.util.Locale;

public abstract class TimedTask extends Task {

    LocalDate deadlineDate;
    LocalTime deadlineTime;

    public TimedTask(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    private String convertLocalDate() {
        int day = deadlineDate.getDayOfMonth();
        String month = deadlineDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int year = deadlineDate.getYear();
        return day + " " + month + " " + year;
    }

    public static class Deadline extends TimedTask {
        public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(taskName, deadlineDate, deadlineTime);
        }

        @Override
        public String status() {
            String date = super.convertLocalDate();

            if (deadlineTime == null) {
                return "[D]" + super.status() + "(by: " + date + ")";
            } else {
                String time = deadlineTime.toString();
                return "[D]" + super.status() + "(by: " + date + " " + time + ")";
            }

        }
    }

    public static class Event extends TimedTask {
        public Event(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(taskName, deadlineDate, deadlineTime);
        }

        @Override
        public String status() {
            String date = super.convertLocalDate();

            if (deadlineTime == null) {
                return "[E]" + super.status() + "(at: " + date + ")";
            } else {
                String time = deadlineTime.toString();
                return "[E]" + super.status() + "(by: " + date + " " + time + ")";
            }

        }
    }
}
