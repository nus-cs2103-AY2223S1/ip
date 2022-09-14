package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a Find Command that when executed by the Poolsheen program
 * prints out all the available tasks that matches the keyword.
 */
public class FindCommand extends Command {
    /**
     * Initialises a Find Command.
     * @param rest The rest of the string that has been parsed.
     */
    public FindCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.size() != 1) {
            throw new PoolsheenException(String.join(" ", rest),
                    "find", "The find command only accepts one word");
        }

        TaskList matchedTasksArray = tl.find(rest.get(0));
        return "Here is what Poolsheen found:\n" + ui.displayList(matchedTasksArray);
    }
}
