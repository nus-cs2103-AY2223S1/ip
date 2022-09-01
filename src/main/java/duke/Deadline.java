package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is specialised Task with a date as deadline
 *
 */
public class Deadline extends Task{
    private String date = "";
    private LocalDate dateProper;

    /**
     * Class Constructor using description of task and date
     * @param taskDescription description of task
     * @param date date that task is due
     */
    public Deadline(String taskDescription, String date) throws DukeException{
        super(taskDescription.replace("deadline ", ""));
        this.date = date;
        try {
            this.dateProper = LocalDate.parse(date);
        } catch (Exception e) {
            throw new DukeException("Date has to follow the following format: YYYY-MM-DD");
        }
    }

    /**
     * Class Constructor using description of task, date and completion status
     * @param taskDescription description of task
     * @param date date that task is due
     * @param isCompleted completion status of task
     */
    public Deadline(String taskDescription, String date, boolean isCompleted) throws DukeException{
        super(taskDescription, isCompleted);
        this.date = date;
        try {
            this.dateProper = LocalDate.parse(date);
        } catch (Exception e) {
            throw new DukeException("Date has to follow thw following format: YYYY-MM-DD");
        }
    }

    /**
     * Returns the String format of Task for display in UI
     * @return String of task
     */
    @Override
    protected String returnDescription() {
        String formattedDate = this.dateProper.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.returnDescription() + " (by: " + formattedDate + ")";
    }

    /**
     * Returns the String format of Task for saving to file
     * @return String of task
     */
    @Override
    protected String toWriteFile(){
        return "D , " + super.toWriteFile() + " , " + this.date;
    }
}

