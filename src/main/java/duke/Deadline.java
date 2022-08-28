package duke;

/**
 * Deadline class to represent a new Deadline task.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Deadline extends Task {
    private String date;

    /**
     * Constructor for Deadline class
     *
     * @param description description of the Deadline task
     * @param isDone indicates if the task is marked as done
     * @param date due date of the Deadline task
     */

    public Deadline(String description, boolean isDone, String date) {
        super(description.trim());
        this.isDone = isDone;
        this.date = date.trim();
        Task.addTaskCount();
    }


    @Override
    public String toString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), this.description, date);
    }


}
