package maria.command;

import java.time.LocalDate;

import maria.TaskManager;
import maria.task.Task;
import maria.task.TaskDeadline;
import maria.task.TaskNoNameException;

/**
 * Represents the command for adding a task with a deadline.
 */
public class CommandAddDeadline extends Command {

    private String name;
    private boolean isDone;
    private LocalDate deadline;

    public CommandAddDeadline(String name, boolean isDone, LocalDate deadline) {
        this.name = name;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {

        try {
            Task task = new TaskDeadline(this.name, this.isDone, this.deadline);
            taskManager.getTaskList().add(task);
            return "Added a deadline task " + task;
        } catch (TaskNoNameException e) {
            System.out.println("Error in creating Deadline. " + e.getMessage());
        }

        return "";

    }
}
