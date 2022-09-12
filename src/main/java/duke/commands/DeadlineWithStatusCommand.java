package duke.commands;

import duke.task.Deadline;
import duke.task.TaskList;

import java.util.Scanner;

public class DeadlineWithStatusCommand extends Command {
    private final String deadlineDescription;
    private final String deadlineBy;
    private final boolean isDone;

    public DeadlineWithStatusCommand(Scanner scanner) {
        deadlineDescription = scanner.next().strip();
        deadlineBy = scanner.next().strip();
        isDone = Boolean.parseBoolean(scanner.reset().skip("\\|").nextLine().strip());
    }

    public String execute(TaskList taskList) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineBy, isDone);
        taskList.add(deadlineTask);
        return "Success";
    }
}
