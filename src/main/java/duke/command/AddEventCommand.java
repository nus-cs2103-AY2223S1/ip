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
     * Constructor for an AddEventCommand.
     * @param desc Description of the event.
     * @param at Date of the event.
     */
    public AddEventCommand(String desc, LocalDate at) {
        this.desc = desc;
        this.at = at;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task newTask = new Event(desc, at);
        tasks.addTask(newTask);
        storage.write(tasks);
        return "Got it. I've added this task:\n\t" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
