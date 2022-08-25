package poolsheen.command;

import java.util.ArrayList;

import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a ListCommand which when executed will cause the Poolsheen program to
 * display all the tasks that Poolsheen remembers.
 */
public class ListCommand extends Command{
    public ListCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            ui.displayList(tl);
        } else {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "bye", "Were you trying to enter 'list'?");
        }
    }
}
