package duke.command;

import duke.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Command to find a Task based on a keyword.
 */
public class FindCommand extends Command {
    private final String[] keywords;

    /**
     * Constructor to populate the keyword used.
     *
     * @param input Used to find a task.
     */
    public FindCommand(String... input) {
        this.keywords = input;
    }

    /**
     * Run the given command as a FindCommand.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @return String containing the message to user
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        return ui.printSomeTasks(taskList.findTasks(this.keywords, taskList));
    }
}