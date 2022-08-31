package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the find command.
     *
     * @param tasks Task list.
     * @param storage Storage.
     */
    public String execute(TaskList tasks, Storage storage) {
        String res = "Here are the matching tasks in your list:";
        for (Task task : tasks.tasks()) {
            if (task.getDescription().contains(input)) {
                res += "\n" + task;
            }
        }
        return res;
    }

}
