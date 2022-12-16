package duke.chatbot.taskmanager.task;

/**
 * ToDoTask is a Task with task information.
 */
public class ToDoTask extends Task {
    public static final String TASK_TYPE = "T";

    /**
     * Creates a new to do task with information indicating the name of the task.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     */
    public ToDoTask(String taskName) {
        super(TASK_TYPE, taskName);
        assert super.getTaskName().length() != 0 : "Task should not be empty";
    }

    /**
     * Creates a new to do task with information indicating the name of the task.
     *
     * @param taskName string of the name of the task
     * @param isCompleted boolean of the completion status of the task.
     */
    public ToDoTask(String taskName, boolean isCompleted) {
        super(TASK_TYPE, taskName, isCompleted);
        assert super.getTaskName().length() != 0 : "Task should not be empty";
    }

    /**
     * Updates the to do task with the updated task name.
     *
     * @param arguments the updated task name
     */
    @Override
    public void update(String... arguments) {
        super.setTaskName(arguments[0]);
    }
}
