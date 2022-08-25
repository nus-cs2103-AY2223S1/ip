package poolsheen.command;

import java.util.ArrayList;

import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.task.Task;
import poolsheen.TaskList;
import poolsheen.Ui;

public class UnmarkCommand extends Command{
    public UnmarkCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty() || rest.size() != 1) {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "unmark", "Please enter 1 appropriate integer");
        } else {
            int pos = java.lang.Integer.parseInt(rest.get(0));
            Task t = tl.get(pos - 1);
            tl.unmark(pos);
            ui.say("Poolsheen thinks you are not done with "
                    + t.description);
        }
    }
}
