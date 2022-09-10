package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class DeleteCommand extends Command {
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
     * @param storage File to be saved to.
     */
    public void execute(TaskList taskList, Storage storage) {
        Task deletedTask = taskList.get(deleteIndex - 1);
        taskList.delete(deleteIndex - 1);
        System.out.println("Noted. I've removed this task:\n" + deletedTask
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        storage.save(taskList);
    }
}
