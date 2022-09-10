package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.Scanner;

public class DeadlineCommand extends Command {
    private final String deadlineDescription;
    private final String deadlineBy;

    /**
     * Parse the command from scanner and store the description as well as date for deadline task.
     *
     * @param scanner User input.
     */
    public DeadlineCommand(Scanner scanner) {
        scanner.useDelimiter("/by");
        deadlineDescription = scanner.next().strip();
        scanner.reset().skip("/by");
        deadlineBy = scanner.nextLine().strip();
    }

    /**
     * Create new deadline task and store it in the task list. Finally, save the task list in storage.
     *
     * @param taskList Task list that stores new deadline task.
     * @param storage File to be saved to.
     */
    public void execute(TaskList taskList, Storage storage) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(deadlineTask);
        System.out.println("Got it. I've added this task:\n" + deadlineTask
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        storage.save(taskList);
    }
}
