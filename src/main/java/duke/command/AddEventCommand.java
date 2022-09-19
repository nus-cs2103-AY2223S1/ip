package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to add an Event to the list of tasks
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String desc;
    private final LocalDate at;

    /**
     * Creates a Command to add an Event.
     *
     * @param desc Description of the event.
     * @param at Date of the event.
     */
    public AddEventCommand(String desc, LocalDate at) {
        this.desc = desc;
        this.at = at;
    }

    /**
     * Adds an Event task to the list of tasks.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the task list.
     * @return Result of the execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task newTask = new Event(desc, at);
        tasks.addTask(newTask);
        storage.write(tasks);
        return "Got it. I've added this task:\n\t" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
