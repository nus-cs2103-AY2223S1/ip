package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command{

    private String messageToFind;

    public FindCommand(String inputMessage) {
        this.messageToFind = inputMessage;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String result = "";
        for (Task task : taskList.getTasks()) {
            if (task.name.contains(messageToFind)) {
                result += task.toString() + "\n";
            }
        }
        return result;
    }
}
