package duke.commands;

import java.time.DateTimeException;

import duke.Deadline;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDescriptionException;



/**
 * Represents a <code>Command</code> to add <code>Deadline</code>.
 */
public class AddDeadlineCommand extends Command {

    private final String DEADLINE_SEPARATOR = " /by ";

    /**
     * Constructs a <code>AddDeadline</code> command.
     *
     * @param description description of command.
     */
    public AddDeadlineCommand(String description) {
        super(description);
    }

    /**
     * Add new deadline based on input to TaskList.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public String execute(Storage storage) {

        try {
            if (description.length() <= 9) {
                throw new DukeEmptyCommandException();
            }

            String[] information = description.substring(9).split(DEADLINE_SEPARATOR, 3);

            if (information.length != 2) {
                throw new DukeInvalidDescriptionException();
            }

            Task newTask = new Deadline(information[0], information[1]);
            TaskList.add(newTask);
            return Ui.printAddTask(newTask);
        } catch (DukeException e) {
            return Ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            return Ui.printError("Sorry i could not recognize the date. Pls use this format \"YYYY-MM-DD\"");
        }

    }
}
