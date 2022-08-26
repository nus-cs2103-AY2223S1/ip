package duke.command;

import duke.exception.DukeException;
import duke.task.TodoTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddTodoCommand extends Command {

    private final TodoTask task;

    public AddTodoCommand(TodoTask task) {
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
