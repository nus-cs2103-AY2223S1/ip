package duke.ui;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static boolean isExit = false;

    public static boolean isExit() {
        return isExit;
    }

    public static String showWelcomeMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    public String readUserInputThenOutputMessage(TaskList taskList, Storage storage, String input) {
        Scanner scanner = new Scanner(input);
        Command command = Parser.parseUserInput(scanner);
        scanner.close();
        String message = command.execute(taskList);
        isExit = command.isExit();
        storage.save(taskList);
        return message;
    }
}
