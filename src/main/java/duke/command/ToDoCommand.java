package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private String details;

    public ToDoCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList list) throws DukeException {
        ToDo task = new ToDo(details);
        list.add(task);
        Ui.added(task);
    }
}
