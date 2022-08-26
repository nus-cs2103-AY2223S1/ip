package duke.taskmanager.task;

import duke.taskmanager.exceptions.EmptyTaskException;

public class ToDoTask extends Task {
    private static final String taskName_TYPE = "T";
    public ToDoTask(String taskName) throws EmptyTaskException {
        super(taskName);
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
    }

    public ToDoTask(String taskName, boolean status) throws EmptyTaskException {
        super(taskName, status);
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getFormattedString() {
        return taskName_TYPE + " | " + (getStatus() ? 1 : 0) + " | " + getTaskName() + "\n";
    }
    @Override
    public String toString() {
        return "[" + taskName_TYPE + "]" + super.toString();
    }
}