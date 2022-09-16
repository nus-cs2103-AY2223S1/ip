package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Can be executed to mark a task.
 */
public class MarkCommand extends Command {

    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.mark(taskNum);
            storage.writeToFile(taskList.list());
            ui.display(String.format("I've marked this task as complete:%n%s%n", task));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task!");
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task task = taskList.mark(taskNum);
            storage.writeToFile(taskList.list());
            return String.format("I've marked this task as complete:%n%s%n", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task!");
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

}
