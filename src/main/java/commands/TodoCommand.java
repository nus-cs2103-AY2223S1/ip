package commands;

import data.exception.DukeException;
import storage.Storage;
import data.TaskList;
import ui.Ui;
import tasks.ToDo;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ToDo toDo = new ToDo(this.description);
        taskList.addToList(toDo);
        storage.save(taskList);
        ui.printAddTask(toDo, taskList.getSize());
    }
}
