package duke.command;

import java.io.IOException;

import duke.Date;
import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.utils.Parser;
import duke.utils.Storage;

/**
 * Handles the "deadline" command.
 * @author Jason
 */
public class DeadlineCommand extends Command {
    private String[] commandDetails;

    public DeadlineCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Handles an deadline task.
     * @param taskList TaskList to add to deadline to.
     * @param storage Storage to save new deadline task.
     * @return String message of running the "deadline" command.
     * @throws DukeException Deadline task has no description or improper syntax.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        try {
            String[] deadlineDetails = commandDetails[1].split(" /by ", 2);
            int correctArrayLength = 2;
            if (deadlineDetails.length == correctArrayLength) {
                Date date = Parser.parseDate(deadlineDetails[1]);
                Deadline deadline = new Deadline(deadlineDetails[0], date);
                return taskList.addTask(deadline, storage);
            } else {
                throw new DukeException("\uD83D\uDE14 OOPS!!! Please follow the syntax for an "
                        + "'deadline' command: event [description] /by [date].");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\uD83D\uDE14 OOPS!!! Please follow the syntax for an "
                    + "'deadline' command: event [description] /by [date].");
        }
    }
}
