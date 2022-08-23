package duke.command;

import duke.exception.DukeException;
import duke.exception.ToDoException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class ToDoCommand implements Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        if (this.description.isEmpty()) {
            throw new ToDoException();
        }

        Task newTask = new ToDo(this.description);
        taskList.addTask(newTask);
        ui.printTaskCreationSuccessMessage(newTask,
                taskList.getTaskListSize());
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
    }
}
