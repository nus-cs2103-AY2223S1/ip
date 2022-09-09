package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class MarkCommand extends Command {
    private final int markIndex;

    public MarkCommand(Scanner scanner) {
        markIndex = scanner.nextInt();
    }

    public void execute(TaskList taskList, Storage storage) {
        taskList.get(markIndex - 1).markAsDone();
        storage.save(taskList);
    }
}
