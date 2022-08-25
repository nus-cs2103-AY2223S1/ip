package puke;

/**
 * A type of task , called a ToDo
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task
     * @param description what the task to do is
     */
    public ToDo (String description) {
        super(description);
    }

    /**
     * Get string representation of ToDo
     * @return string representation of ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the format to save ToDo on hard disk
     * @return String representation of ToDo save format
     */
    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "T | 1 | " + this.description;
        } else {
            return "T | 0 | " + this.description;
        }
    }
} 
