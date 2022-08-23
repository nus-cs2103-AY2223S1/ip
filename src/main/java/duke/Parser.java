package duke;

import duke.command.*;

import java.util.Scanner;

public class Parser {

    public static ICommand parse(String input) {
        try {
            Scanner sc = new Scanner(input);
            String command = sc.next();
            if (!CommandType.commandMap.containsKey(command)) {
                sc.nextLine(); // Move scanner to next line
                throw new DukeException("I'm sorry, but I don't understand that.");
            }
            CommandType type = CommandType.commandMap.get(command);
            switch (type) {
                case TODO:
                case DEADLINE:
                case EVENT:
                    // new AddCommand
                    break;
                case LIST:
                    // new ListCommand
                    break;
                case MARK:
                    // new MarkCommand
                    break;
                case UNMARK:
                    // new UnMarkCommand
                    break;
                case DELETE:
                    // new DeleteCommand
                    break;
                case SAVE:
                    // new SaveCommand
                    break;
                case BYE:
                    // new ExitCommand
                    break;
            }
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
            return new EmptyCommand();
        }
    }
}
