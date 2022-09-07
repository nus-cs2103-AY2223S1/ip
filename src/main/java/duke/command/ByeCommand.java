package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that exits the program.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand.
     *
     * @param command "bye".
     */
    public ByeCommand(String command) {
        super(command);
        assert(command == "bye");
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        this.execute(taskList, storage);
        ui.printWithDivider("Bye. Hope to see you again soon!");
    }
}
