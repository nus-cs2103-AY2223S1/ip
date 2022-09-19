package Duke.commands;


import java.util.ArrayList;
import java.util.List;
import Duke.*;

import java.io.IOException;

public class DeleteCommands extends TaskCommands {

    private String input;

    public DeleteCommands(String input) throws DukeException {
        super(input);
    }

    /**
     * Executes the command to delete a task in the task list.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            String line1 = "I've removed this task: ";
            String line2 = tasks.getTaskToString(taskNumber);
            tasks.removeTask(taskNumber);
            String reply = line1 + line2;
            return reply;
        } else {
            throw new DukeException("This task does not exists");
        }
    }
}
