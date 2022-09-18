package candice.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;

import java.util.Locale;

/**
 * Extends the Task class, adding on to the encapsulation of a Task object by adding dates and potentially times
 * associated to certain tasks, specifically deadlines and events.
 */
public abstract class TimedTask extends Task {
    /** The date associated to the task */
    LocalDate date;
    /** The time associated to the task */
    LocalTime time;

    /**
     * Constructor for a task with a date and potentially a time associated to it.
     * If no time is associated to the task, the time variable in the object will be null.
     *
     * @param taskName The name of the task.
     * @param date The date associated to this task.
     * @param time The time associated to this task. Null if no time was associated to this task.
     */
    public TimedTask(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    /**
     * Uses the instance of LocalDate associated to this TimedTask object to return the String representation of the
     * dates that will be displayed to users of this program.
     *
     * @return The String representation of dates that are displayed to users.
     */
    String convertLocalDate() {
        int day = date.getDayOfMonth();
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int year = date.getYear();
        return day + " " + month + " " + year;
    }

    /**
     * Returns a String representing all the relevant information of a timed task, in the format of whether the task
     * has been finished, the name of the task, the date and the time.
     *
     * @return A String containing the type of task, whether it has been finished, the name of the task, the
     * deadline date and the deadline time if there was a time inputted in the storage format.
     */
    @Override
    public String getStorageDescription() {
        String desc = super.getStorageDescription();
        String descWithDate = desc.substring(0, desc.length() - 1) + ", " + this.date;

        if (this.time == null) {
            return descWithDate + ", no time given\n";
        } else {
            return descWithDate + ", " + this.time + "\n";
        }
    }

    /**
     * Represents deadline tasks which are tasks that have a date and potentially a time associated to it as the
     * deadline of the task. If no time is associated to the deadline task, the time associated to the task will be
     * null.
     */
    public static class Deadline extends TimedTask {
        /**
         * Constructor for a deadline task with a date and time associated to it.
         * If there was no time specified, the time encapsulated in this Deadline object will be null.
         *
         * @param taskName The name of the task.
         * @param deadlineDate The date associated to this task.
         * @param deadlineTime The time associated to this task. Null if no time was associated to this task.
         */
        public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(taskName, deadlineDate, deadlineTime);
        }

        /**
         * Returns a String indicating that this Task object encapsulates a deadline task, whether the task has been
         * finished, the name of the task and the deadline time. The deadline time will include the deadline date and
         * deadline time if the deadline time was inputted.
         *
         * @return A String indicating that this task is a deadline task, whether it has been finished, the name of the
         * deadline task and the deadline time.
         */
        @Override
        public String getStatus() {
            String deadlineDate = convertLocalDate();
            String dateAndTime = this.time == null
                                 ? deadlineDate
                                 : deadlineDate + " " + this.time;

            return "[D]" + super.getStatus() + " (by: " + dateAndTime + ")";
        }

        /**
         * Returns a String representing all the relevant information of a deadline task, in the format of type of task,
         * whether the task has been finished, the name of the task, the deadline date and the deadline time.
         *
         * @return A String containing the type of task, whether it has been finished, the name of the task, the
         * deadline date and the deadline time if there was a time inputted in the storage format.
         */
        @Override
        public String getStorageDescription() {
            return "[D], " + super.getStorageDescription();
        }
    }

    /**
     * Represents event tasks which are tasks that have a date and potentially two times associated to it as the
     * start and end time of the event. If no time is associated to the event task, the times associated to the task
     * will be null.
     */
    public static class Event extends TimedTask {
        /** The end time associated to the task*/
        LocalTime eventEndTime;

        /**
         * Constructor for an event task with a date and 2 times associated to it.
         * If there was no time specified, the times encapsulated in this Event object will be null.
         *
         * @param taskName The name of the task.
         * @param eventDate The date associated to this task.
         * @param eventStartTime The start time associated to this task.
         * @param eventEndTime The end time associated to this task.
         */
        public Event(String taskName, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
            super(taskName, eventDate, eventStartTime);
            this.eventEndTime = eventEndTime;
        }

        /**
         * Returns a String indicating that this Task object encapsulates an event task, whether the task has been
         * finished, the name of the task and the event start to end time. The event time will include the event date
         * and event start to end time if the event times were inputted.
         *
         * @return A String indicating that this task is an event, whether it has been finished, the name of the
         * event and the event time.
         */
        @Override
        public String getStatus() {
            String eventDate = convertLocalDate();
            String dateAndTime = (this.time == null || this.eventEndTime == null)
                                 ? eventDate
                                 : eventDate + " " + time + "-" + eventEndTime;

            return "[E]" + super.getStatus() + " (at: " + dateAndTime + ")";
        }

        /**
         * Returns a String representing all the relevant information of an event, in the format of type of task,
         * whether the task has been finished, the name of the task, the event date and the event start to end time.
         * This String representation will be placed in a text file as storage.
         *
         * @return A String containing the type of task, whether it has been finished, the name of the task, the
         * event date, the even start time and event end time if there was a time inputted in the storage format.
         */
        @Override
        public String getStorageDescription() {
            String desc = super.getStorageDescription();
            String descWithoutNewLine = desc.substring(0, desc.length() - 1);

            if (this.time == null || this.eventEndTime == null) {
                return "[E], " + desc;
            } else {
                return "[E], " + descWithoutNewLine + ", " + this.eventEndTime + "\n";
            }
        }
    }
}
