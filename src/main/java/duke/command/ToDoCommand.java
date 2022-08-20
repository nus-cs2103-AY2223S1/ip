package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.printAddTask(taskList.addTasks(new ToDo(this.description)));
        ui.printSizeOfList(taskList.size());
        storage.save(taskList.getTasks());
    }
}
