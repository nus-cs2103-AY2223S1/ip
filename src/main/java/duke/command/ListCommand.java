package duke.command;

import duke.storage.TaskRecords;
import duke.ui.BotUI;
public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {
        System.out.print(ui.showList(taskList));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
