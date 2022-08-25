package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.NoSuchTaskException;
import duke.task.Task;
import duke.task.TasksController;

import java.util.ArrayList;

/**
 * FindCommand class will execute the find command.
 */
public class FindCommand extends Command {

    /**
     * Unmarks a task
     * @param controller Duke task controller
     * @param ui Duke UI
     * @param storage Duke IO processor
     */
    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in your keyword: ");
        String keyword = ui.inputText();
        ArrayList<Task> result = controller.findByKeyword(keyword);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < result.size(); ++i) {
            s.append(i + 1).append(". ").append(result.get(i).toString()).append("\n");
        }
        ui.display(s.toString(), false, false, false, false, true);
    }
}
