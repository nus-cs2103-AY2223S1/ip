package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddTodoCommand extends Command {

    ToDoTask task;

    AddTodoCommand(ToDoTask task) {
        super(CommandType.ADD_TODO);
        this.task = task;
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.addNewTask(task);
        } catch (DukeException exception) {
            output = exception.getMessage();
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
