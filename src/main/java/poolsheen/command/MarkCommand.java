package poolsheen.command;

import java.util.ArrayList;
import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.task.Task;
import poolsheen.Ui;

/**
 * Represents a MarkCommand which when executed will cause the Poolsheen program to
 * mark the task of that respective position which Poolsheen remembers.
 */
public class MarkCommand extends Command{
    public MarkCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty() || rest.size() != 1) {
            throw new IncompleteCommandException(String.join(" ", rest), "mark", "Please enter 1 appropriate integer");
        } else {
            int pos = java.lang.Integer.parseInt(rest.get(0));
            Task t = tl.get(pos - 1);
            tl.mark(pos);
            ui.say("Poolsheen thinks you are done with "
                + t.description);
        }
    }
}
