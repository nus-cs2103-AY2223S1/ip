package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

public class AddToDoCommand extends Command {

    public AddToDoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ToDo todo = new ToDo(description.substring(5), false);
            tasks.add(todo);
            ui.printAddTask(todo, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
