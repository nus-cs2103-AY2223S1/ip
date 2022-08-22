package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.ToDo;

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
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ToDo toDo = new ToDo(this.description);
        taskList.addToList(toDo);
        storage.save(taskList);
        ui.printAddTask(toDo, taskList.getSize());
    }
}
