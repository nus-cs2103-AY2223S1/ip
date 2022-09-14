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
    public DeleteCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new PoolsheenException(String.join(" ", rest),
                    "delete", "Please enter 1 appropriate integer");
        }

        int pos = java.lang.Integer.parseInt(rest.get(0));
        boolean isOutOfBounds = (pos - 1) > (tl.getSize() - 1);

        if (isOutOfBounds) {
            throw new PoolsheenException("Index out of bounds", "delete", "Enter an appropriate integer");
        }

        Task t = tl.get(pos - 1);
        tl.deleteTask(pos);
        return ui.say("Poolsheen has forgot: " + t.getDescription()
                + " and you now have " + tl.getSize() + " tasks left");
    }
}
