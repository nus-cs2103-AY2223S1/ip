import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;

import java.util.Locale;

public abstract class TimedTask extends Task {
    LocalDate date;

    public TimedTask(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    String convertLocalDate() {
        int day = date.getDayOfMonth();
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int year = date.getYear();
        return day + " " + month + " " + year;
    }

    public static class Deadline extends TimedTask {
        LocalTime deadlineTime;

        public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(taskName, deadlineDate);
            this.deadlineTime = deadlineTime;
        }

        @Override
        public String getStatus() {
            String deadlineDate = convertLocalDate();
            String dateAndTime = this.deadlineTime != null
                                 ? deadlineDate + " " + this.deadlineTime
                                 : deadlineDate;

            return "[D]" + super.getStatus() + " (by: " + dateAndTime + ")";
        }

        @Override
        public String getStorageDescription() {
            String finishedStatus = super.finished ? "finished" : "unfinished";

            if (this.deadlineTime == null) {
                return "[D], " + finishedStatus + ", " + this.taskName + ", " + this.date
                        + ", no time given\n";
            } else {
                return "[D], " + finishedStatus + ", " + this.taskName + ", " + this.date + ", "
                        + this.deadlineTime + "\n";
            }
        }
    }

    public static class Event extends TimedTask {
        LocalTime eventStartTime;
        LocalTime eventEndTime;

        public Event(String taskName, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
            super(taskName, eventDate);
            this.eventStartTime = eventStartTime;
            this.eventEndTime = eventEndTime;
        }

        @Override
        public String getStatus() {
            String eventDate = convertLocalDate();
            String dateAndTime = this.eventStartTime != null
                                 ? eventDate + " " + eventStartTime + "-" + eventEndTime
                                 : eventDate;

            return "[E]" + super.getStatus() + " (at: " + dateAndTime + ")";
        }

        @Override
        public String getStorageDescription() {
            String finishedStatus = super.finished ? "finished" : "unfinished";
            if (this.eventStartTime == null) {
                return "[D], " + finishedStatus + ", " + this.taskName + ", " + this.date
                        + ", no time given\n";
            } else {
                return "[D], " + finishedStatus + ", " + this.taskName + ", " + this.date + ", "
                        + this.eventStartTime + ", " + this.eventEndTime + "\n";
            }
        }
    }
}
