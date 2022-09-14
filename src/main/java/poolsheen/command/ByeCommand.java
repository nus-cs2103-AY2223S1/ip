package poolsheen.command;

import java.util.ArrayList;

import poolsheen.Poolsheen;
import poolsheen.PoolsheenException;
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
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (!rest.isEmpty()) {
            throw new PoolsheenException(String.join(" ", rest),
                    "bye", "Try to enter only 'bye'");
        }
        Poolsheen.forceExit();
        return ui.say("Goodbye :(");
    }
}
