import java.util.Scanner;

public class DeadlineCommand extends Command {
    private final String deadlineDescription;
    private final String deadlineBy;

    DeadlineCommand(Scanner scanner) {
        scanner.useDelimiter("/by");
        deadlineDescription = scanner.next().strip();
        scanner.reset().skip("/by");
        deadlineBy = scanner.nextLine().strip();
    }

    public void execute(TaskList taskList, Storage storage) {
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineBy);
        taskList.add(deadlineTask);
        System.out.println("Got it. I've added this task:\n" + deadlineTask
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        storage.save(taskList);
    }
}
