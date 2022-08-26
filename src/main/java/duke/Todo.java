package duke;

/**
 * Task to be completed at any time
 */
public class Todo extends Task{
    /**
     * A constructor to intialize the Todo object with the description
     *
     * @param description The task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string that represents the Todo object
     * @return String A string that represents the current object
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }

    /**
     * Returns a string that represents the Todo object in text file format
     * @return String A string that represents the current object
     */
    @Override
    public String toFileString() {
        return "T | " +  this.getFileStatus() + " | " + super.toString();
    }
}
