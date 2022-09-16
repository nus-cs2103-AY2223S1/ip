package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Can be executed to add a TodoTask.
 */
public class AddTodoCommand extends Command {

    private final Task todoTask;

    public AddTodoCommand(String taskName) {
        todoTask = new TodoTask(taskName);
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(todoTask);
            storage.writeToFile(taskList.list());
            ui.display(String.format("Added new todo task:%n%s%n", todoTask));
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.add(todoTask);
            storage.writeToFile(taskList.list());
            return String.format("I've added a new todo task:%n%s%n", todoTask);
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

}
