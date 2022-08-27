package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.wrapPrint("Bye. Hope to see you again soon!");
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ByeCommand;
    }
}
