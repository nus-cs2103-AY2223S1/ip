package tasks;

/**
 * Todo represents a Tasks.Task without any datetime objects associated with it.
 * Inherits from Tasks.Task.
 */
public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    public ToDo(String taskDescription, boolean isComplete) {
        super(taskDescription);
        setIsComplete(isComplete);
    }

    @Override
    public String getEncodedValue() {
        return String.format("[T]#%s#%s", getTaskDescription(), getIsComplete());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
