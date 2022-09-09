package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private final int taskNum;

    public UnmarkCommand(int taskNum) {
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
            return String.format("I've marked this task as incomplete:%n%s%n", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task!");
        } catch (IOException e) {
            throw new DukeException("Could not write to file");
        }
    }

}
