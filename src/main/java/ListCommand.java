import java.io.IOException;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Run the given command as an ListCommand.
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        ui.printListOfTasks(taskList);
    }
}
