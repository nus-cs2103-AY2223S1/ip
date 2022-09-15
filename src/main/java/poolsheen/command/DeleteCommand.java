package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;
import poolsheen.task.Task;

/**
 * Represents a DeleteCommand which when executed will cause the Poolsheen program to
 * delete one task of the respective index that Poolsheen remembers.
 */
public class DeleteCommand extends Command {
    /**
     * Initialises a Delete Command.
     * @param rest The rest of the string that has been parsed.
     */
    public DeleteCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new PoolsheenException(String.join(" ", rest),
                    "delete", "Please enter 1 appropriate integer");
        }
        int pos;
        try {
            pos = java.lang.Integer.parseInt(rest.get(0));
        } catch (NumberFormatException e) {
            throw new PoolsheenException(e.getMessage(), "delete", "Enter a number");
        }

        boolean isOutOfBounds = ((pos - 1) > (tl.getSize() - 1)) || (pos <= 0);

        if (isOutOfBounds) {
            throw new PoolsheenException("Index out of bounds", "delete", "Enter an appropriate integer");
        }

        Task t = tl.get(pos - 1);
        tl.deleteTask(pos);
        return ui.say("Poolsheen has forgot: " + t.getDescription()
                + " and you now have " + tl.getSize() + " tasks left");
    }
}
