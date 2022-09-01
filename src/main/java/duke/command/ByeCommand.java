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
