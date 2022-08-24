package duke.commands;

import duke.Ui;
import duke.task.TaskList;

/**
 * Command to terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean getShouldExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineCommand;
    }
}
