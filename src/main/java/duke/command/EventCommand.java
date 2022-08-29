package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeMissingSpecifierException;
import duke.task.Task;

import java.time.format.DateTimeParseException;

/**
 * EventCommand is a Command that handles event.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */

public class EventCommand extends Command {
    String description;
    String at;

    /**
     * Constructor for EventCommand.
     *
     * @param description A String description of the event task.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Outputs the event task being added.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException The exception thrown when an action is unauthorized by Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitDescription = description.split(" /at ", 2);
        if (splitDescription[0].equals(description)) {
            throw new DukeMissingSpecifierException("event", " /at ");
        }
        try {
            String instruction = splitDescription[0];
            String at = splitDescription[1];
            this.at = at;
            Task event = tasks.addEvent(instruction, at);
            ui.displayAdd(event, tasks.getSize());
        } catch (DateTimeParseException dtp) {
            throw new DukeInvalidDateException();
        }
    }
}
