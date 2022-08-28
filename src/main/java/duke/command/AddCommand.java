package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
import duke.Task;
import duke.ToDo;


public class AddCommand extends Command { //Creating a duke.ToDo duke.Task and adding to the taskList
    String description;
    boolean isDone;

    public AddCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task todo = new ToDo(this.description, this.isDone);
        taskList.addTask(todo);
        storage.saveData(taskList);
        if (!storage.checkIsLoadingFile()) { UI.added(todo); }
    }

}
