package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeMissingSpecifierException;
import duke.task.Deadline;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitDescription = description.split(" /by ", 2);
        if (splitDescription[0].equals(description)) {
            throw new DukeMissingSpecifierException("deadline", " /by ");
        }
        try {
            String instruction = splitDescription[0];
            String by = splitDescription[1];
            this.by = by;
            Task deadline = tasks.addDeadline(instruction, by);
            ui.displayAdd(deadline, tasks.getSize());
        } catch (DateTimeParseException dtp) {
            throw new DukeInvalidDateException();
        }
    }
}
