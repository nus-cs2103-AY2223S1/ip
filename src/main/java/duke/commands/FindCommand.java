package duke.commands;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(Scanner scanner) {
        keyword = scanner.skip(" ").nextLine();
    }

    public String execute(TaskList tasklist) {
        List<String> matchingTasks = new ArrayList<>();
        matchingTasks.add("Here are the matching tasks in your list:");
        ListIterator<Task> listIterator = tasklist.getListIterator();
        while (listIterator.hasNext()) {
            int taskIndex = listIterator.nextIndex() + DISPLAYED_INDEX_OFFSET;
            Task task = listIterator.next();
            addMatchingTask(matchingTasks, taskIndex, task);
        }
        return String.join("\n", matchingTasks);
    }

    private void addMatchingTask(List<String> matchingTasks, int taskIndex, Task task) {
        if (task.doesContain(keyword)) {
            matchingTasks.add(taskIndex + "." + task);
        }
    }
}
