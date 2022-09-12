package duke.commands;

import duke.task.TaskList;
import duke.task.ToDo;

import java.util.Scanner;

public class ToDoWithStatusCommand extends Command {
    private final String toDoDescription;
    private final boolean isDone;

    public ToDoWithStatusCommand(Scanner scanner) {
        toDoDescription = scanner.next().strip();
        isDone = Boolean.parseBoolean(scanner.reset().skip("\\|").nextLine().strip());
    }

    public String execute(TaskList taskList) {
        ToDo toDoTask = new ToDo(toDoDescription, isDone);
        taskList.add(toDoTask);
        return "Success";
    }
}
