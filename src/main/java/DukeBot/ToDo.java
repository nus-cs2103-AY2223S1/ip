package DukeBot;

/**
 * Encapsulates the ToDo subclass of Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Get the type of Task.
     *
     * @return "T" indicating ToDo.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Get time of the ToDo.
     *
     * @return empty String
     */
    @Override
    public String getTime() {
        return "";
    }

    /**
     * Get string representation of ToDo.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}
