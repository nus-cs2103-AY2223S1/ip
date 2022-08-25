package poolsheen.command;

import java.util.ArrayList;

import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.task.Event;
import poolsheen.Ui;

/**
 * Represents an EventCommand which when executed will cause the Poolsheen program to
 * add one Event task for Poolsheen to remember.
 */
public class EventCommand extends Command{
    public EventCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "event", "Events need a description and time");
        } else if (!rest.contains("/at")) {
            throw new IncompleteCommandException(String.join(" ", rest), "event", "event commands need an '/at'");
        } else {
            String descE = String.join(" ", rest.subList(0, rest.indexOf("/at")));
            String timeE = String.join(" ", rest.subList(rest.indexOf("/at") + 1, rest.size()));
            if (descE.length() == 0 || timeE.length() == 0) {
                throw new IncompleteCommandException(String.join(" ", rest),
                        "event", "event commands must specify a description and time");
            } else {
                Event e = new Event(descE, false, timeE);
                tl.add(e);
                ui.say("Poolsheen now remembers: " + descE);
            }
        }
    }
}
