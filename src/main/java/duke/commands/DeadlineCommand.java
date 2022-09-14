package duke.commands;

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
     */
    public String execute(TaskList taskList) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(deadlineTask);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in your list.",
                deadlineTask, taskList.getSize());
    }
}
