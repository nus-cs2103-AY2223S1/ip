package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.Ui;

/**
 * Command to execute adding a Deadline to a TaskList
 * @author Nephelite
 * @version 0.1
 */
public class DeadlineCommand extends Command {
    private String command;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of a DeadlineCommand
     * @param command
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public DeadlineCommand(String command, TaskList tasks, Ui ui) {
        this.command = command;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeException {
        String[] returnedArray = command.split(" /by ");
        if (returnedArray.length <= 0) {
            throw new DukeException("your duke.command is incomplete." +
                    "\nPlease use the [help] duke.command to check the proper usage of [deadline].");
        } else if (returnedArray.length == 1) {
            throw new DukeException("your duke.command is missing the [/by] component, or the second half of the duke.command." +
                    "\nPlease use the [help] duke.command to check the proper usage of [deadline].");
        } else if (returnedArray.length > 2) {
            String secondHalf = "";
            for (int i = 1; i < returnedArray.length; i++) {
                secondHalf += returnedArray[i] + " ";
            }
            returnedArray[1] = secondHalf;
        }
        Deadline deadline = new Deadline(returnedArray[0], returnedArray[1]);
        tasks.add(deadline);
        ui.addTask(deadline, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
