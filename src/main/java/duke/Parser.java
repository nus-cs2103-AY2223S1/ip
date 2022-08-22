package duke;

import duke.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser {
    private enum Commands {
        TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, SAVE
    }
    private static final HashMap<String, Commands> commandMap = new HashMap<>(Map.of(
            "todo", Commands.TODO,
            "deadline", Commands.DEADLINE,
            "event", Commands.EVENT,
            "list", Commands.LIST,
            "bye", Commands.BYE,
            "mark", Commands.MARK,
            "unmark", Commands.UNMARK,
            "delete", Commands.DELETE,
            "save", Commands.SAVE
    ));

    public static Command parse(String input) {
        try {
            Scanner sc = new Scanner(input);
            String command = sc.next();
            if (!commandMap.containsKey(command)) {
                sc.nextLine(); // Move scanner to next line
                throw new DukeException("I'm sorry, but I don't understand that.");
            }
            Commands type = commandMap.get(command);
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
            return EmptyCommand;
        }
    }
}
