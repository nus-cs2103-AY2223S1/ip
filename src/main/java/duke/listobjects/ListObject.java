package duke.listobjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to store list items with task and status of completion
 */
public class ListObject implements Serializable, Comparable<ListObject> {
    private final String task;
    private String time;
    private int status;

    /**
     * Constructs a ListObject with given task description and status
     *
     * @param task   String representing task description
     * @param status int with value 1 if task is complete and 0 otherwise
     */
    public ListObject(String task, int status) {
        this.task = task;
        this.status = status;
    }

    /**
     * Constructs a ListObject with given task description and status
     *
     * @param task   String representing task description
     * @param time   String representing task deadline or start and end time
     * @param status int with value 1 if task is complete and 0 otherwise
     */
    public ListObject(String task, String time, int status) {
        this.task = task;
        this.time = time;
        this.status = status;
    }

    /**
     * Returns task description
     *
     * @return String representing task decription
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns task status
     *
     * @return int with value 1 if task is complete and 0 otherwise
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Returns the task's completion status
     *
     * @return String indicating status of completion of task
     */
    public String showStatusIndicator() {
        if (this.status == 1) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * Switches the status of completion of task
     */
    public void switchStatus() {
        if (this.status == 1) {
            this.status = 0;
        } else {
            this.status = 1;
        }
    }

    /**
     * Checks if the task description contains the given keyword
     *
     * @param keyword String representing the keyword to check for in task description
     * @return boolean true if the description contains the word and false otherwise
     */
    public boolean hasWord(String keyword) {
        return this.task.contains(keyword);
    }

    /**
     * Reads String representing date and time and returns it in alternate format
     *
     * @return String representing date and time in format MMM dd yyyy HH:mm HH:mm as date, start and end times
     */

    public String formatDateTime(Type type) {


        String txt = this.time;
        String[] words = txt.split(" ");
        String date = words[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");


        switch (type) {

        case EVENT:
            String start = words[1];
            String end = words[2];

            //format date of form yyyy-MM-dd
            LocalDate eventDate = LocalDate.parse(date, formatter);
            String eventDateNew = eventDate.format(formatNew);

            //format time of form HH:mm (24h clock)
            LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ISO_LOCAL_TIME);
            String timeStart = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ISO_LOCAL_TIME);
            String timeEnd = endTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

            return " (on: " + eventDateNew + " from: " + timeStart + " to: " + timeEnd + ")";

        case DEADLINE:
            String time = words[1];

            //format date of form yyyy-MM-dd
            LocalDate deadline = LocalDate.parse(date, formatter);
            String dateNew = deadline.format(formatNew);

            //format time of form HH:mm (24h clock)
            LocalTime deadlineTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
            String timeNew = deadlineTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

            return " (by: " + dateNew + " at " + timeNew + ")";

        default:
            return "Are you sure you have recorded the time?";

        }
    }

    /**
     * Compares two ListObjects based on deadline, if present, and task otherwise
     *
     * @param obj the object to be compared.
     * @return -1 if this has earlier deadline than object, 1 otherwise and 0 if equal
     */
    @Override
    public int compareTo(ListObject obj) {
        int compareDate = this.time.compareTo(obj.time);
        int compareTask = this.task.compareToIgnoreCase(obj.task);
        int compareStatus = this.status - obj.status;

        if (compareDate != 0) {
            return compareDate;
        } else {
            if (compareTask != 0) {
                return compareTask;
            } else {
                return compareStatus;
            }
        }
    }

    /**
     * Returns String representation of the ListObject
     *
     * @return String representing the ListObject
     */
    @Override
    public String toString() {
        return this.showStatusIndicator() + this.getTask();
    }


    /**
     * Represents different types of tasks in ListObject
     */
    public enum Type {
        TODO,
        EVENT,
        DEADLINE
    }

}
