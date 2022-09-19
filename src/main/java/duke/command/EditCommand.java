package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A class that represents a command that marks a task in the to-do list as done or undone. */
public class EditCommand extends Command {

    private String keyword;
    private String input;

    public EditCommand(String keyword, String input) {
        this.keyword = keyword;
        this.input = input;
    }

    /**
     * Executes the editing of a task to mark the task as done or undone, after receiving the appropriate input from
     * the user.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     * @param ui The UI object that displays messages to the user.
     * @param storage The storage used to save the task to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.keyword.equals("update")) {
            String[] splitInput = this.input.split(" ", 3);
            return tasks.updateTask(splitInput[0], splitInput[1], splitInput[2], ui, storage);
        }
        return tasks.markTask(this.keyword, this.input, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
