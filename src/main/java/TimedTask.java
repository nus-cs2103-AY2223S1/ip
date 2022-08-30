import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;

import java.util.Locale;

public abstract class TimedTask extends Task {
    LocalDate date;

    abstract String convertToTaskDateAndTime();

    public TimedTask(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    public static class Deadline extends TimedTask {
        LocalTime deadlineTime;

        public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(taskName, deadlineDate);
            this.deadlineTime = deadlineTime;
        }

        String convertToTaskDateAndTime() {
            int day = date.getDayOfMonth();
            String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            int year = date.getYear();
            String deadlineDate = day + " " + month + " " + year;

            return this.deadlineTime != null
                   ? deadlineDate + " " + this.deadlineTime
                   : deadlineDate;
        }

        @Override
        public String showStatus() {
            return "[D]" + super.showStatus() + " (by: " + convertToTaskDateAndTime() + ")";
        }

        @Override
        public String showTaskListTextDescription() {
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

        String convertToTaskDateAndTime() {
            int day = date.getDayOfMonth();
            String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            int year = date.getYear();
            String eventDate = day + " " + month + " " + year;

            return this.eventStartTime != null
                    ? eventDate + " " + eventStartTime + "-" + eventEndTime
                    : eventDate;
        }

        @Override
        public String showStatus() {
            return "[E]" + super.showStatus() + " (at: " + convertToTaskDateAndTime() + ")";
        }

        @Override
        public String showTaskListTextDescription() {
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
