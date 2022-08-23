package duke.commands;

import java.io.IOException;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command{
    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String message = "";
        int length = taskList.length();
        if (length == 0) {
            message += "List of tasks is currently empty.";
        } else {
            message += "Here are the tasks in your list:\n";
            for (int i = 0; i < length; i++) {
                if (i < length - 1) {
                    message += String.format("%d. " + taskList.index(i).toString() + "\n", i + 1);
                } else {
                    message += String.format("%d. " + taskList.index(i).toString(), i + 1);
                }
            }
        }
        ui.print(message);
    }
}
