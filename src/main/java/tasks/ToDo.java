package tasks;

/**
 * ToDo represents a Tasks.Task without any datetime objects associated with it.
 * Inherits from Tasks.Task.
 */
public class ToDo extends Task {
    /**
     * Overloaded constructor for ToDo object.
     *
     * @param taskDescription Description of ToDo.
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Overloaded constructor for ToDo object.
     *
     * @param taskDescription Description of ToDo.
     * @param isComplete      Completion status of ToDo.
     */
    public ToDo(String taskDescription, boolean isComplete) {
        super(taskDescription);
        setIsComplete(isComplete);
    }

    /**
     * Returns the encoded representation of the current ToDo to be saved to an external storage.
     *
     * @return String representation of current ToDo for saving to Storage.
     */
    @Override
    public String getEncodedValue() {
        return String.format("[T]#%s#%s", getTaskDescription(), getIsComplete());
    }

    /**
     * Returns string representation of ToDo to be printed to terminal.
     *
     * @return Returns string representation of ToDo to be printed to terminal.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
