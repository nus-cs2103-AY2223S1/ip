package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;
import poolsheen.task.Task;

/**
 * Represents a MarkCommand which when executed will cause the Poolsheen program to
 * mark the task of that respective position which Poolsheen remembers.
 */
public class MarkCommand extends Command {
    /**
     * Initialises a Mark Command.
     * @param rest The rest of the string that has been parsed.
     */
    public MarkCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty() || rest.size() != 1) {
            throw new PoolsheenException(String.join(" ", rest),
                    "mark", "Please enter 1 appropriate integer");
        }

        int pos = java.lang.Integer.parseInt(rest.get(0));
        boolean isOutOfBounds = ((pos - 1) > (tl.getSize() - 1)) || (pos <= 0);

        if (isOutOfBounds) {
            throw new PoolsheenException("Index out of bounds", "mark", "Enter an appropriate integer");
        }

        Task t = tl.get(pos - 1);
        tl.mark(pos);
        return ui.say("Poolsheen thinks you are done with " + t.getDescription());
    }
}
