package poolsheen.command;

import java.util.ArrayList;

import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.task.ToDo;
import poolsheen.Ui;

/**
 * Represents a MarkCommand which when executed will cause the Poolsheen program to
 * add a ToDo task for Poolsheen to remember.
 */
public class ToDoCommand extends Command{
    public ToDoCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "todo", "The description of a todo cannot be empty");
        } else {
            String descTD = String.join(" ", rest);
            ToDo t = new ToDo(descTD, false);
            tl.add(t);
            ui.say("Poolsheen now remembers: " + descTD);
        }
    }
}
