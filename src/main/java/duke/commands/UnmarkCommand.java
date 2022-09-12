package duke.commands;

import duke.task.TaskList;

import java.util.Scanner;

public class UnmarkCommand extends Command {
    private static final int DISPLAYED_INDEX_OFFSET = 1;

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
     */
    public String execute(TaskList taskList) {
        return taskList.get(unmarkIndex - DISPLAYED_INDEX_OFFSET).markAsUndone();
    }
}
