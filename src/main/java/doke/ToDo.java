package doke;

import java.time.LocalDate;

/**
 * Represent the ToDo task. Extends the Task class.
 */
public class ToDo extends Task{

    /**
     * a public constructor method for the ToDo class.
     *
     * @param description a description for the ToDo Task
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Returns null
     *
     * @return null
     */
    @Override
    public LocalDate getTime() {
        return null;
    }

    /**
     * Returns a String representing the type of ToDo task.
     *
     * @return String "T"
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a string representation of the ToDO object.
     *
     * @return a string representation of the ToDO object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
