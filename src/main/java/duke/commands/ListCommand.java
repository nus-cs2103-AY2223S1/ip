package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            // Display duke.task as 1-index
            str.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        ui.printTextWithDivider(str.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
