package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class IncorrectCommand extends Command {
    /**
     * Advance the scanner past the current line.
     *
     * @param scanner User input.
     */
    public IncorrectCommand(Scanner scanner) {
        scanner.nextLine();
    }

    /**
     * Print error message for invalid command.
     */
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means:-(");
    }
}
