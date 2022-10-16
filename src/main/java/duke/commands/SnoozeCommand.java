package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * A <code>Command</code> class that handles the snoozing of a <code>Task</code>
 * Note that this <code>Command</code> will only work correctly for <code>Deadline</code> and <code>Event</code>
 * since they have a DateTime, unlike <code>Todo</code>
 */
public class SnoozeCommand extends Command {
    private final String[] arguments;

    /**
     * A constructor for the <code>SnoozeCommand</code> class
     *
     * @param args Arguments from user input
     */
    public SnoozeCommand(String[] args) {
        this.arguments = args;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException, DateTimeParseException {
        String[] taskDesc = Arrays.copyOfRange(arguments, 1, arguments.length);
        taskDesc = String.join(" ", taskDesc).split(" /to ");
        if (taskDesc.length < 2) {
            throw new DukeException("You're missing some details!\n"
                    + "\nThe command format should be:\nsnooze TASK_NUMBER /to DATETIME\n"
                    + "\nThe format of the DATETIME should be as follows: "
                    + "dd/MM/yyyy HHmm");
        }

        String response;
        try {
            int taskNum = Integer.parseInt(taskDesc[0]) - 1;
            response = tl.snoozeTask(taskNum, taskDesc[1]);
            storage.refreshList(tl.getTasks());
        } catch (IndexOutOfBoundsException indexErr) {
            response = "This task number does not exist in the list!";
        } catch (NumberFormatException parseErr) {
            response = "Is that even a number?! Try entering an actual number next time";
        }
        return response;
    }
}
