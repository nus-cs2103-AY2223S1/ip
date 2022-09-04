import java.util.Scanner;

public class DeleteCommand extends Command {
    private final int deleteIndex;

    DeleteCommand(Scanner scanner) {
        deleteIndex = scanner.nextInt();
    }

    public void execute(TaskList taskList, Storage storage) {
        Task deletedTask = taskList.get(deleteIndex - 1);
        taskList.delete(deleteIndex - 1);
        System.out.println("Noted. I've removed this task:\n" + deletedTask
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        storage.save(taskList);
    }
}
