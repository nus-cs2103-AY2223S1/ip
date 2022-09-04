import java.util.Scanner;

public class ToDoCommand extends Command {
    private final String toDoDescription;

    ToDoCommand(Scanner scanner) {
        toDoDescription = scanner.nextLine().strip();
    }

    public void execute(TaskList taskList, Storage storage) {
        if (toDoDescription.isBlank()) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } else {
            ToDo toDoTask = new ToDo(toDoDescription);
            taskList.add(toDoTask);
            System.out.println("Got it. I've added this task:\n" + toDoTask
                    + "\nNow you have " + taskList.getSize() + " tasks in your list.");
            storage.save(taskList);
        }
    }
}
