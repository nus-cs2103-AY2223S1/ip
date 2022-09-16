package duke.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeMissingSpecifierException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeadlineCommand is a Command that handles deadline.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description A String description of the deadline task.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Outputs the deadline task being added.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String[] splitDescription = description.split(" /by ", 2);
        if (splitDescription[0].equals(description)) {
            throw new DukeMissingSpecifierException("deadline", " /by ");
        }
        try {
            String instruction = splitDescription[0];
            String by = splitDescription[1];
            this.by = by;
            Task deadline = tasks.addDeadline(instruction, by);
            storage.save(tasks);
            return ui.displayAdd(deadline, tasks.getSize());
        } catch (DateTimeParseException dtp) {
            throw new DukeInvalidDateException();
        }
    }
}
