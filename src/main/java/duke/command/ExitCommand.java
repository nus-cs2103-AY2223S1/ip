package duke.command;

import duke.storage.TaskRecords;
import duke.ui.BotUI;
public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
