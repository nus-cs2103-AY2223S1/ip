package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class MarkCommand extends Command {
    private final int markIndex;

    /**
     * Parse the command from scanner and store the index for the task to be marked.
     *
     * @param scanner User input.
     */
    public MarkCommand(Scanner scanner) {
        markIndex = scanner.nextInt();
    }

    /**
     * Mark the specified task in the task list as done. Finally, save the task list in storage.
     *
     * @param taskList Task list that contains the specified task.
     * @param storage File to be saved to.
     */
    public void execute(TaskList taskList, Storage storage) {
        taskList.get(markIndex - 1).markAsDone();
        storage.save(taskList);
    }
}
