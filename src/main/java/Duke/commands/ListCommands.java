package Duke.commands;


import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ListCommands extends Command {


    /**
     * Executes the command to print out the tasks in the list.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ArrayList<String> text = new ArrayList<>();
        text.add("Here are the tasks in your list:");
        for (int i = 1; tasks.isValidTaskNumber(i); i++) {
            text.add(i + ". " + tasks.getTaskToString(i));
        }
        return text;
    }
}
