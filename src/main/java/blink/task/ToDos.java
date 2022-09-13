package blink.task;

import java.time.LocalDate;

/**
 * ToDos task that contains a description and boolean to indicate if
 * it has been marked.
 */
public class ToDos extends Task {

    /**
     * Constructor to create ToDos task object
     *
     * @param description Description of ToDos task object.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * String representation of ToDos Object.
     *
     * @return [T] to represent deadline and information indicating
     *     if its marked and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + this.tagToString();
    }

    /**
     * String representation of ToDos object in storage file.
     *
     * @return String to represent the information of current ToDos object
     */
    @Override
    public String saveString() {
        String taskMark = "|" + (this.isDone ? 1 : 0) + "| ";
        return "T " + taskMark + this.description + this.saveTagString() + "\n";
    }

    /**
     * Compares with ToDos to see if date matches.
     *
     * @param anoDate Date to check equality of current ToDos object with
     * @return False as ToDos object have no date to compare with
     */
    @Override
    public boolean checkDate(LocalDate anoDate) {
        return false;
    }
}

