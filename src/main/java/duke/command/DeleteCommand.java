package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Can be executed to delete a task.
 */
public class DeleteCommand extends Command {

    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.delete(taskNum);
            storage.writeToFile(taskList.list());
            ui.display(String.format("I've deleted this task:%n%s%n", task));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task!");
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task task = taskList.delete(taskNum);
            storage.writeToFile(taskList.list());
            return String.format("I've deleted this task:%n%s%n", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task!");
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

}
