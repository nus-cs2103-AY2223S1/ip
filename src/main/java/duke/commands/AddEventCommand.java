package duke.commands;

import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDescriptionException;

import java.time.DateTimeException;

/**
 * Represents a <code>Command</code> to add <code>Event</code>.
 */
public class AddEventCommand extends Command {

    /**
     * Constructs a <code>AddEventCommand</code> command.
     *
     * @param description description of command.
     */
    public AddEventCommand(String description) {
        super(description);
    }

    /**
     * Add new event based on input to TaskList.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public void execute(Storage storage) {

        try {
            if (description.length() <= 6) {
                throw new DukeEmptyCommandException();
            }

            String[] information = description.substring(6).split(" /at ", 3);

            if (information.length != 2) {
                throw new DukeInvalidDescriptionException();
            }

            Task newTask = new Event(information[0], information[1]);
            TaskList.add(newTask);
            Ui.printAddTask(newTask);
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            Ui.printError("Sorry i could not recognize the date. Pls use this format \"YYYY-MM-DD\"");
        }

    }

}
