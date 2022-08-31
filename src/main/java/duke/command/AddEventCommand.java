package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * AddEventCommand is a Command when the user wants to add an Event task.
 */
public class AddEventCommand extends Command {

    private String details;

    /**
     * Constructor for AddEventCommand.
     *
     * @param details The details of the task.
     */
    public AddEventCommand(String details) {
        this.details = details;
    }

    /**
     * Adds an Event task to the TaskList.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!details.contains("/at")) {
            throw new DukeException("OOPS!!! Time of event required. (/at)");
        }
        String[] split = details.split("/");
        String desc = split[0];
        if (desc.equals("") || desc.equals(" ")) {
            throw new DukeException("OOPS!!! Description of event is required.");
        }
        String at = split[1].split(" ", 2)[1];
        Event event = new Event(desc, at);
        tasks.add(event);
        return event.toString();
    }
}
