package poolsheen.command;

import java.util.ArrayList;

import poolsheen.IncompleteCommandException;
import poolsheen.Storage;
import poolsheen.task.Deadline;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a DeadlineCommand which when executed will cause the Poolsheen program to
 * create a new Deadline task for Poolsheen to remember.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "deadline", "Deadlines need a description and time");
        } else if (!rest.contains("/by")) {
            throw new IncompleteCommandException(String.join(" ", rest),
                    "deadline", "deadline commands need a '/by'");
        } else {
            String descD = String.join(" ", rest.subList(0, rest.indexOf("/by")));
            String timeD = String.join(" ", rest.subList(rest.indexOf("/by") + 1, rest.size()));
            if (descD.length() == 0 || timeD.length() == 0) {
                throw new IncompleteCommandException(String.join(" ", rest),
                        "deadline", "deadline commands must specify a description and time");
            } else {
                Deadline d = new Deadline(descD, false, timeD);
                tl.add(d);
                ui.say("Poolsheen now remembers: " + descD);
            }
        }
    }
}
