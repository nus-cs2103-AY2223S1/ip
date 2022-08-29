package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.ui.Ui;

/**
 * This class encapsulates an Event Command
 */
public class EventCommand extends Command {

    private String description;
    private String at;

    /**
     * Constructs a new Event Command
     * @param description A description of the event
     * @param at When the event is occurring
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Event event = new Event(this.description, this.at);
        taskList.addToList(event);
        storage.save(taskList);
        return ui.printAddTask(event, taskList.getSize());
    }
}
