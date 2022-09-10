package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class UnmarkCommand extends Command {
    private final int unmarkIndex;

    /**
     * Parse the command from scanner and store the index for the task to be unmarked.
     *
     * @param scanner User input.
     */
    public UnmarkCommand(Scanner scanner) {
        unmarkIndex = scanner.nextInt();
    }

    /**
     * Mark the specified task in the task list as not done yet. Finally, save the task list in storage.
     *
     * @param taskList Task list that contains the specified task.
     * @param storage File to be saved to.
     */
    public void execute(TaskList taskList, Storage storage) {
        taskList.get(unmarkIndex - 1).markAsUndone();
        storage.save(taskList);
    }
}
