package ip.command;

import java.util.Scanner;

import ip.TaskList;
import ip.task.Task;

/**
 * Command to find tasks matching specified keyword.
 */
public class FindCommand extends Command {
    /** Options following the find command */
    private final Scanner options;

    public FindCommand(Scanner options) {
        this.options = options;
    }

    /**
     * Finds tasks with descriptions matching given keyword.
     *
     * @param taskList List to search.
     */
    @Override
    public void execute(TaskList taskList) {
        String keyword = "";
        if (options.hasNext()) {
            keyword = options.nextLine().trim();
        }
        int i = 1;
        System.out.println("Tasks containing \"" + keyword + "\"");
        for (Task task : taskList.tasks) {
            if (task.hasString(keyword)) {
                System.out.println(i + ". " + task);
                i++;
            }
        }
    }
}
