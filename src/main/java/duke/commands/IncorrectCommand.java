package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class IncorrectCommand extends Command {
    public IncorrectCommand(Scanner scanner) {
        scanner.nextLine();
    }

    public void execute(TaskList taskList, Storage storage) {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means:-(");
    }
}
