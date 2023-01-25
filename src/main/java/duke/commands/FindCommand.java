package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to find whether a command with the task name exists.
 */
public class FindCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        String wordToFind = ui.userString().split(" ")[1];
        sb.append("     Here are the matching tasks in your list:\n");
        String toFind = taskList.getAllTasks().stream()
                                            .filter(t -> t.getTaskName()
                                            .contains(wordToFind))
                                            .map(t -> t.toString()).reduce("", (a, b) -> "\t" + a + "\n\t" + b);
        sb.append(toFind);

        return sb.toString();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
