package duke.listobjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Represents Deadlines which are ListObjects that store deadline (date and time) with the task
 */
public class Deadline extends ListObject{

    String doBy;

    /**
     * Constructs a Deadline object with given task description and status, with unspecified deadline
     * @param task String description of task
     * @param status int representing completion status as 1 if finished and 0 otherwise
     */
    public Deadline(String task, int status) {
        super(task, status);
        this.doBy= "not specified";
    }

    /**
     * Constructs a Deadline object with given task description, status, and deadline
     * @param task String description of tas
     * @param status int representing completion status as 1 if finished and 0 otherwise
     * @param doBy String representing deadline (date and time) for task
     */
    public Deadline(String task, int status, String doBy){
        super(task, status);
        this.doBy = doBy;
    }

    /**
     * Returns the String representation of the Deadline object
     * @return String representation of Deadline object
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + formatDateTime(doBy) + ")";
    }


    /**
     * Reads String representing date and time and returns an alternate String representation of it
     * @param txt String representing date and time in the format of yyyy-MM-dd HH:mm
     * @return String representation of date and time in alternate format of MMM dd yyy HH:mm
     */
    public String formatDateTime(String txt){

        String[] words = txt.split(" ");
        String date = words[0];
        String time = words[1];

        //formate date of form yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deadline = LocalDate.parse(date, formatter);
        DateTimeFormatter formatNew = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String dateNew = deadline.format(formatNew);

        //format time of form HH:mm (24h clock)
        LocalTime deadlineTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        String timeNew = deadlineTime.format(DateTimeFormatter.ISO_LOCAL_TIME);

        return dateNew + " at " + timeNew;
    }

}
