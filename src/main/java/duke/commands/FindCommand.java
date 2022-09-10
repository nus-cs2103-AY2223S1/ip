package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ListIterator;
import java.util.Scanner;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(Scanner scanner) {
        keyword = scanner.nextLine().strip();
    }

    public void execute(TaskList tasklist, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        ListIterator<Task> listIterator = tasklist.getListIterator();
        while (listIterator.hasNext()) {
            int taskIndex = listIterator.nextIndex() + 1;
            Task task = listIterator.next();
            printIfMatchingTask(taskIndex, task);
        }
    }

    private void printIfMatchingTask(int taskIndex, Task task) {
        if (task.doesContain(keyword)) {
            System.out.println(taskIndex + "." + task);
        }
    }
}
