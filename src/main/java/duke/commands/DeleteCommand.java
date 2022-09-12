package duke.commands;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class DeleteCommand extends Command {
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    private final int deleteIndex;

    /**
     * Parse the command from scanner and store the index for the task to be deleted.
     *
     * @param scanner User input.
     */
    public DeleteCommand(Scanner scanner) {
        deleteIndex = scanner.nextInt();
    }

    /**
     * Delete specified task from the task list. Finally, save the task list in storage.
     *
     * @param taskList Task list that contains the specified task.
     */
    public String execute(TaskList taskList) {
        Task deletedTask = taskList.get(deleteIndex - DISPLAYED_INDEX_OFFSET);
        taskList.delete(deleteIndex - DISPLAYED_INDEX_OFFSET);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in your list.",
                deletedTask, taskList.getSize());
    }
}
