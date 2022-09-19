package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

/** A class that represents a command to delete a task from the to-do list **/
public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the deleting of a task from the to-do list, after receiving the appropriate input from the user.
     * Removes the task from the file.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     * @param ui The UI object that displays messages to the user.
     * @param storage The storage used to save the task to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.removeFromFile(this.input);
            return tasks.deleteTask(this.input, ui);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        return "oops, I am unable to execute the command";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
