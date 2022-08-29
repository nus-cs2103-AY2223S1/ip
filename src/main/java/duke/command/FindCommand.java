package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class FindCommand extends Command {

    String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the find command.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "Here are the matching tasks in your list:";
        for (Task task : tasks.tasks()) {
            if (task.getDescription().contains(input)) {
                res += "\n" + task;
            }
        }
        ui.print(res);
    }

}
