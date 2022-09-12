package maria.command;

import maria.TaskManager;
import maria.task.Task;
import maria.task.TaskNoNameException;
import maria.task.TaskTodo;

/**
 * Represents the command for adding a task with no timings specified.
 */
public class CommandAddTodo extends Command {

    private String name;
    private boolean isDone;

    public CommandAddTodo(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {

        try {
            Task task = new TaskTodo(this.name, this.isDone);
            taskManager.getTaskList().add(task);
            return "Added a todo task " + task;
        } catch (TaskNoNameException e) {
            System.out.println("Error in creating Todo. " + e.getMessage());
        }

        return "";

    }
}
