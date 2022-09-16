package duke.listobjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to store list items with task and status of completion
 */
public class ListObject implements Serializable, Comparable<ListObject> {
    private String task;
    String time;
    private int status;

    /**
     * Constructs a ListObject with given task description and status
     * @param task String representing task description
     * @param status int with value 1 if task is complete and 0 otherwise
     */
    public ListObject(String task, int status) {
        this.task = task;
        this.status = status;
    }

    /**
     * Constructs a ListObject with given task description and status
     * @param task String representing task description
     * @param time String representing task deadline or start and end time
     * @param status int with value 1 if task is complete and 0 otherwise
     */
    public ListObject(String task, String time, int status) {
        this.task = task;
        this.time = time;
        this.status = status;
    }


    /**
     * Returns task description
     * @return String representing task decription
     */
    public String getTask(){
        return this.task;
    }

    /**
     * Returns task status
     * @return int with value 1 if task is complete and 0 otherwise
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * Returns the task's completion status
     * @return String indicating status of completion of task
     */
        public String showStatusIndicator() {
            if (this.status==1) {
                return "[X] ";
            } else {
                return "[ ] ";
            }
        }


    /**
     * Switches the status of completion of task
     */
    public void switchStatus() {
        if (this.status==1) {
            this.status=0;
        } else {
            this.status=1;
        }
    }

    /**
     * Checks if the task description contains the given keyword
     * @param keyword String representing the keyword to check for in task description
     * @return boolean true if the description contains the word and false otherwise
     */
    public boolean hasWord(String keyword) {
        return this.task.contains(keyword);
    }

    /**
     * Reads String representing event time and returns it in alternate format
     * @return String representing event time in format MMM dd yyyy HH:mm HH:mm as date, start and end times
     */

    public String formatDateTime(String type) {

        String txt = this.time;

        if(type.equals("e")) {
            String[] words = txt.split(" ");
            String date = words[0];
            String start = words[1];
            String end = words[2];

            //format date of form yyyy-MM-dd
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(date, formatter);
            DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String dateNew = deadline.format(formatNew);

            //format time of form HH:mm (24h clock)
            LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ISO_LOCAL_TIME);
            String timeStart = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ISO_LOCAL_TIME);
            String timeEnd = endTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

            return dateNew + " from: " + timeStart + " to: " + timeEnd;
        }

        if(type.equals("d")){
            String[] words = txt.split(" ");
            String date = words[0];
            String time = words[1];

            //format date of form yyyy-MM-dd
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(date, formatter);
            DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");
            String dateNew = deadline.format(formatNew);

            //format time of form HH:mm (24h clock)
            LocalTime deadlineTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
            String timeNew = deadlineTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

            return dateNew + " at " + timeNew;
        } else {
            return "Try again!";
        }
    }

    /**
     * Compares two ListObjects based on deadline, if present, and task otherwise
     * @param obj the object to be compared.
     * @return -1 if this has earlier deadline than object, 1 otherwise and 0 if equal
     */
    @Override
    public int compareTo(ListObject obj) {

        if(this.time.isEmpty() || obj.time.isEmpty()) {
            int res = this.task.compareTo(obj.task);
            if(res == 0 && this.status >= obj.status){
                return -1;
            }
            if(res==0 && this.status < obj.status){
                return 1;
            }
            else{
                return res;
            }
        } else {
            return obj.time.compareTo(this.time);
        }
    }



    /**
     * Returns String representation of the ListObject
     * @return String representing the ListObject
     */
    @Override
    public String toString() {
        return this.showStatusIndicator()+ this.getTask();
    }

}