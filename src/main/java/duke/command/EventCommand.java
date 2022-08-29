package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeMissingSpecifierException;
import duke.task.Task;

import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    String description;
    String at;

    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeMissingSpecifierException, DukeInvalidDateException {
        String[] splitDescription = description.split(" /at ", 2);
        if (splitDescription[0].equals(description)) {
            throw new DukeMissingSpecifierException("deadline", " /at ");
        }
        try {
            String instruction = splitDescription[0];
            String at = splitDescription[1];
            this.at = at;
            Task event = tasks.addDeadline(instruction, at);
            ui.displayAdd(event, tasks.getSize());
        } catch (DateTimeParseException dtp) {
            throw new DukeInvalidDateException();
        }
    }
}
