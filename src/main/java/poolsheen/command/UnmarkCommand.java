package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;
import poolsheen.task.Task;

/**
 * Represents a MarkCommand which when executed will cause the Poolsheen program to
 * unmark the task of that respective position which Poolsheen remembers.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty() || rest.size() != 1) {
            throw new PoolsheenException(String.join(" ", rest),
                    "unmark", "Please enter 1 appropriate integer");
        } else {
            int pos = java.lang.Integer.parseInt(rest.get(0));
            if ((pos - 1) > (tl.getSize() - 1)) {
                throw new PoolsheenException("Index out of bounds", "unmark", "Enter an appropriate integer");
            } else {
                Task t = tl.get(pos - 1);
                tl.unmark(pos);
                return ui.say("Poolsheen thinks you are not done with " + t.getDescription());
            }
        }
    }
}
