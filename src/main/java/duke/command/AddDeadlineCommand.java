package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to add a Deadline to the list of tasks.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final String desc;
    private final LocalDate by;

    /**
     * Creates a Command to add a Deadline.
     *
     * @param desc Description of the task.
     * @param by Deadline of the task.
     */
    public AddDeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Adds a Deadline task to the list of tasks.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the task list.
     * @return Result of the execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task newTask = new Deadline(desc, by);
        tasks.addTask(newTask);
        storage.write(tasks);
        return "Got it. I've added this task:\n\t" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
