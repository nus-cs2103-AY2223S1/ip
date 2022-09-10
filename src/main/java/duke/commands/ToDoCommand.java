package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.Scanner;

public class ToDoCommand extends Command {
    private final String toDoDescription;

    /**
     * Parse the command from scanner and store the description for to-do task.
     *
     * @param scanner User input.
     */
    public ToDoCommand(Scanner scanner) {
        toDoDescription = scanner.nextLine().strip();
    }

    /**
     * Create new to-do task and store it in the task list. Finally, save the task list in storage.
     *
     * @param taskList Task list that stores new to-do task.
     * @param storage File to be saved to.
     */
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
