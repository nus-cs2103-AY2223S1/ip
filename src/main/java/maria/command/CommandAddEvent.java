package maria.command;

import java.time.LocalDate;

import maria.TaskManager;
import maria.task.Task;
import maria.task.TaskEvent;
import maria.task.TaskNoNameException;

/**
 * Represents the command for adding a task with a start and end date.
 */
public class CommandAddEvent extends Command {

    private String name;
    private boolean done;
    private LocalDate start;
    private LocalDate end;

    public CommandAddEvent(String name, boolean done, LocalDate start, LocalDate end) {
        this.name = name;
        this.done = done;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {

        try {
            Task task = new TaskEvent(this.name, this.done, this.start, this.end);
            taskManager.getTaskList().add(task);
        } catch (TaskNoNameException e) {
            System.out.println("Error in creating Event. " + e.getMessage());
        }

    }
}
