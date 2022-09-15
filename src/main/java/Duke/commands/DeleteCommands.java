package Duke.commands;

import Duke.*;

import java.io.IOException;

public class DeleteCommands extends TaskCommands {

    public DeleteCommands(String fullInput) throws DukeException {
        super(fullInput);
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've removed this task: ");
            ui.printLine(tasks.getTaskToString(taskNumber));
            tasks.removeTask(taskNumber);
            storage.updateTask(taskNumber, Constants.DELETE);
        } else {
            throw new DukeException("This task does not exists");
        }
    }
}
