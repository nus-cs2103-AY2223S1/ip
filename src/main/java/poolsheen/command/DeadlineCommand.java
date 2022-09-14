package poolsheen.command;

import java.util.ArrayList;
import java.util.List;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;
import poolsheen.task.Deadline;

/**
 * Represents a DeadlineCommand which when executed will cause the Poolsheen program to
 * create a new Deadline task for Poolsheen to remember.
 */
public class DeadlineCommand extends Command {
    /**
     * Initialises a Deadline Command.
     * @param rest The rest of the string that has been parsed.
     */
    public DeadlineCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new PoolsheenException(String.join(" ", rest),
                    "deadline", "Deadlines need a description and time");
        }

        if (!rest.contains("/by")) {
            throw new PoolsheenException(String.join(" ", rest),
                    "deadline", "deadline commands follow the format: 'deadline _desc_ /by _time_'");
        }

        List<String> descArray = rest.subList(0, rest.indexOf("/by"));
        List<String> timeArray = rest.subList(rest.indexOf("/by") + 1, rest.size());
        String descD = String.join(" ", descArray);
        String timeD = String.join(" ", timeArray);
        boolean isEmptyDesc = descD.length() == 0;
        boolean isEmptyTime = timeD.length() == 0;

        if (isEmptyDesc || isEmptyTime) {
            throw new PoolsheenException(String.join(" ", rest),
                    "deadline", "Deadline commands must specify a description and time");
        }

        Deadline d = new Deadline(descD, false, timeD);
        tl.add(d);
        return ui.say("Poolsheen now remembers: " + descD);
    }
}
