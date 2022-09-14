package duke.commands;

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
     */
    public String execute(TaskList taskList) {
        if (toDoDescription.isBlank()) {
            return "OOPS!!! The description of a todo cannot be empty.";
        } else {
            ToDo toDoTask = new ToDo(toDoDescription);
            taskList.add(toDoTask);
            return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in your list.",
                    toDoTask, taskList.getSize());
        }
    }
}
