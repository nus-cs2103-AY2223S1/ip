package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a ListCommand which when executed will cause the Poolsheen program to
 * display all the tasks that Poolsheen remembers.
 */
public class ListCommand extends Command {
    /**
     * Initialises a List Command.
     * @param rest The rest of the string that has been parsed.
     */
    public ListCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            return ui.displayList(tl);
        } else {
            throw new PoolsheenException(String.join(" ", rest),
                    "list", "Try to enter only 'list'");
        }
    }
}
