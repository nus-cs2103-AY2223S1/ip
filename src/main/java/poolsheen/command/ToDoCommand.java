package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;
import poolsheen.task.ToDo;

/**
 * Represents a MarkCommand which when executed will cause the Poolsheen program to
 * add a ToDo task for Poolsheen to remember.
 */
public class ToDoCommand extends Command {
    /**
     * Initialises a ToDo Command.
     * @param rest The rest of the string that has been parsed.
     */
    public ToDoCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new PoolsheenException(String.join(" ", rest),
                    "todo", "The description of a todo cannot be empty");
        }

        String descTD = String.join(" ", rest);
        ToDo t = new ToDo(descTD, false);
        tl.add(t);
        return ui.say("Poolsheen now remembers: " + descTD);
    }
}
