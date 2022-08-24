package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A class that encapsulates the find command.
 */
public class FindCommand extends Command {

    /** The query inputted by the user */
    private final String query;

    /**
     * The class constructor.
     *
     * @param query The query inputted by the user
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Handles the execution behaviour of the find command.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke bot.
     * @param storage The storage of data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = tasks.find(query);
        if (result.size() == 0) {
            ui.printMessage("No results match your search :(");
        } else {
            ui.printMessage("Here are the matching tasks in your list:");
        }
        for (Task t : result) {
            ui.printMessage(t.toString());
        }
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "find";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "find " + this.query;
    }
}
