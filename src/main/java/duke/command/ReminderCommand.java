package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class ReminderCommand extends Command {

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).needToRemind()) {
                returnString.append(taskList.get(i));
            }
        }
        return returnString.toString();
    }
}
