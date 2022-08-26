package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listString.append(String.format("%d.", i + 1));
            listString.append(tasks.get(i).toString());
            listString.append("\n");
        }
        if (listString.length() > 0) {
            listString.setLength(listString.length() - 1);
        }
        ui.wrapPrint(listString.toString());
    }
}
