package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.ToDo;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class TodoCommand implements Command {
    private ToDo todo;

    public TodoCommand(String desc) {
        this.todo = new ToDo(desc, false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(todo);
            storage.appendToFile(todo);
            ui.addTask(todo);
            ui.printListSize(taskList);
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
