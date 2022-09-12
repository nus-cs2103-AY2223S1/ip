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

    public String readUserInputThenOutputMessage(TaskList taskList, Storage storage, String input) {
        assert !isExit : "isExit should be false here, and can only be changed after this line of code";
        Scanner scanner = new Scanner(input);
        Command command = Parser.parseUserInput(scanner);
        scanner.close();
        String message = command.execute(taskList);
        isExit = command.isExit();
        storage.overwriteSave(taskList);
        return message;
    }
}
