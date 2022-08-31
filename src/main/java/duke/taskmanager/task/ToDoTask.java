package duke.taskmanager.task;

import duke.taskmanager.exceptions.EmptyTaskException;

public class ToDoTask extends Task {
    private static final String taskName_TYPE = "T";

    /**
     * Creates a new to do task with information indicating the name of the task.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     * @throws EmptyTaskException if taskName is empty
     */
    public ToDoTask(String taskName) throws EmptyTaskException {
        super(taskName);
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Creates a new to do task with information indicating the name of the task.
     *
     * @param taskName string of the name of the task
     * @param isCompleted boolean of the completion status of the task.
     * @throws EmptyTaskException if taskName is empty
     */
    public ToDoTask(String taskName, boolean isCompleted) throws EmptyTaskException {
        super(taskName, isCompleted);
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Returns whether the task is empty. Returns false as this is not an empty task.
     *
     * @return false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Formats the details of the task into a format that can be read and written by
     * the task manager.
     *
     * @return details of the task in a string format readable and writable by taskManager
     */
    @Override
    public String getFormattedString() {
        return taskName_TYPE + " | " + (isCompleted() ? 1 : 0) + " | " + getTaskName() + "\n";
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return "[" + taskName_TYPE + "]" + super.toString();
    }
}