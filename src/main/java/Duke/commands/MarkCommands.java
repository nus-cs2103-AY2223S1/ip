package Duke.commands;

import Duke.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkCommands extends TaskCommands {


    /**
     * Class constructor.
     *
     * @param input The input provided by the user.
     */

    public MarkCommands(String input) throws DukeException {
        super(input);
    }

    /**
     * Executes the command to mark a task in the task list.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ArrayList<String> text = new ArrayList<>();
            text.add("I've marked this task as done!");
            tasks.markAsDone(taskNumber);
            storage.updateTask(taskNumber, Constants.MARK);
            text.add(tasks.getTaskToString(taskNumber));
            return text;
        } else {
            throw new DukeException("This task does not exist.");
        }
    }
}
