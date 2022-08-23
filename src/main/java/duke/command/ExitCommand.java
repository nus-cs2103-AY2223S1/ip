package duke.command;

import duke.storage.TaskRecords;
import duke.ui.BotUI;
public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    private static final String COMMAND_ID = "exit";

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
