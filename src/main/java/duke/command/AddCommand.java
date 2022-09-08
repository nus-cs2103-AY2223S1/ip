package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A class that represents a command for the bot to add a task to the to-do list. */
public class AddCommand extends Command {

    private String keyword;
    private String input;

    public AddCommand(String keyword, String commandInput) {
        this.keyword = keyword;
        this.input = commandInput;
    }

    /**
     * Executes the user command by adding a task to the to-do list, after receiving the appropriate input from the user.
     * Writes the task to the file, to save the task.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     * @param ui The UI object that displays messages to the user.
     * @param storage The storage used to save the task to file.
     * @throws DukeException If the input from the user is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (this.input.isBlank()) {
                throw new DukeException("oops, the description of your task seems to be incomplete!");
            }
            tasks.addTask(this.keyword, this.input);
            storage.writeToFile(this.keyword + "," + this.input);
            return ui.showAddedTask(tasks);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
