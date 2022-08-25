package poolsheen.command;

import java.util.ArrayList;

import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a ByeCommand which when executed will cause the Poolsheen program to say goodbye
 * and stop running.
 */
public class ByeCommand extends Command {
    public ByeCommand(ArrayList<String> rest) {
        super(true, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            ui.say("Goodbye :(");
        } else {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "bye", "Were you trying to enter 'bye'?");
        }
    }
}
