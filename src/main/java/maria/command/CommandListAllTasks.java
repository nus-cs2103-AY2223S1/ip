package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public class CommandListAllTasks extends Command {

    /**
     * Executes the command.
     * @param taskList The list of all the tasks
     * @param ui The user interface object
     * @param storage The storage object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        if (taskList.size() > 0) {

            StringBuilder sb = new StringBuilder();
            sb.append("Here are the list of tasks you have:\n");

            for (int i = 0; i < taskList.size(); i++) {
                sb.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }

            ui.showText(sb.toString());

        } else {
            ui.showText("You have no tasks.");
        }

    }
}
