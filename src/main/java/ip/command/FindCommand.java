package ip.command;

import ip.TaskList;
import ip.exception.*;
import ip.task.Task;

import java.util.Scanner;

public class FindCommand extends Command {
    private String commandGiven;
    private Scanner options;

    public FindCommand(String commandGiven, Scanner options) {
        this.commandGiven = commandGiven;
        this.options = options;
    }
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
