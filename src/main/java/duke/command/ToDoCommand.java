package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class ToDoCommand extends Command{

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        assert storage != null;
        assert taskList != null;
        assert ui != null;

        String output = "";
        Task task = new ToDo(description);
        taskList.add(task);
        output += ui.printAddTask(task.toString());
//        output += taskList.toString();
        storage.saveFileData(taskList);
        return output;
    }
}
