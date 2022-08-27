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
    private boolean done;
    private LocalDate deadline;

    public CommandAddDeadline(String name, boolean done, LocalDate deadline) {
        this.name = name;
        this.done = done;
        this.deadline = deadline;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {

        try {
            Task task = new TaskDeadline(this.name, this.done, this.deadline);
            taskManager.getTaskList().add(task);
        } catch (TaskNoNameException e) {
            System.out.println("Error in creating Deadline. " + e.getMessage());
        }

    }
}
