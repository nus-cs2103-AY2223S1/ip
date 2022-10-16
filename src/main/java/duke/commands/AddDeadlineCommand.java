package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.task.Deadline;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * A <code>Command</code> class that handles the adding of a <code>Deadline</code>
 */
public class AddDeadlineCommand extends Command {
    private final String[] arguments;

    /**
     * A constructor for the <code>AddDeadlineCommand</code> class
     *
     * @param args Arguments from user input
     */
    public AddDeadlineCommand(String[] args) {
        this.arguments = args;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException, DateTimeParseException {
        String[] taskDesc = Arrays.copyOfRange(arguments, 1, arguments.length);
        taskDesc = String.join(" ", taskDesc).split(" /by ");
        if (taskDesc.length < 2 || taskDesc[0].equals(" ")) {
            throw new DukeException("You're missing some details!\nRemember to include the description "
                    + "followed by '/by dd/MM/yyyy HHmm'");
        }
        Deadline newDeadline = new Deadline(taskDesc[0], taskDesc[1]);
        tl.addTask(newDeadline);
        String response = "Got it! I have added this task to your list:\n  "
                + newDeadline.toString()
                + "\nNow you have " + tl.getSize() + " tasks in the list.";
        storage.refreshList(tl.getTasks());
        return response;
    }
}
