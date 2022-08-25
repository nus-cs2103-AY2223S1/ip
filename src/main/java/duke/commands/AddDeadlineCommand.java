package duke.commands;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeMissingArgumentException;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeMissingArgumentException {
        try {
            String[] str = description.substring(9).split(" /by ");
            Deadline deadline = new Deadline(str[0], str[1], false);
            tasks.add(deadline);
            ui.printAddTask(deadline, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException("OOPS!!! The description and/or the time of a deadline cannot be empty.");
        }
    }
}
