package duke.command;

import duke.oop.Storage;
import duke.task.Task;
import duke.oop.TaskList;
import duke.oop.Ui;

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
