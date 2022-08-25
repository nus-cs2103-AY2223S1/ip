package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;
import duke.exceptions.DukeMissingArgumentException;

public class AddToDoCommand extends Command {

    public AddToDoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeMissingArgumentException {
        try {
            ToDo todo = new ToDo(description.substring(5), false);
            tasks.add(todo);
            ui.printAddTask(todo, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
