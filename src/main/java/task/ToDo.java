package task;

/**
 * This class encapsulates the idea of a to-do.
 */
public class ToDo extends Task {

    /**
     * Constructor for To-Do.
     *
     * @param description what the to-do is.
     * @param status      whether it has been completed.
     */
    public ToDo(String description, boolean status) {
        super(description, status);
    }

    /**
     * Converts the to-do to string representation to be stored in text file.
     *
     * @return a string in format T | F | read book
     */
    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "T | " + status + " | " + super.toString() + "\n";
    }

    /**
     * Converts the to-do to string representation for user.
     *
     * @return a string in format [T][ ] read book
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.toString();
    }
}