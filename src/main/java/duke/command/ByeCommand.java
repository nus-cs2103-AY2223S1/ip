package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit Duke.
 */
public class ByeCommand extends Command {
    /**
     * Create a new ByeCommand.
     */
    public ByeCommand() {
        super();
        isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.wrapPrint("Bye. Hope to see you again soon!");
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ByeCommand;
    }
}
