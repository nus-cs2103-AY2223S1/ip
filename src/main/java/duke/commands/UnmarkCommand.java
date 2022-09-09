package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class UnmarkCommand extends Command {
    private final int unmarkIndex;

    public UnmarkCommand(Scanner scanner) {
        unmarkIndex = scanner.nextInt();
    }

    public void execute(TaskList taskList, Storage storage) {
        taskList.get(unmarkIndex - 1).markAsUndone();
        storage.save(taskList);
    }
}
