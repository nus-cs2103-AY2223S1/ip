package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        // ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ", 2)));
        String firstWord = words[0];
        String restOfCommand = words[1];
        switch (firstWord) {
        case "list":
            return new ListCommand(restOfCommand);
        case "bye":
            return new ExitCommand(restOfCommand);
        case "mark":
            return new MarkCommand(restOfCommand);
        case "unmark":
            return new MarkCommand(restOfCommand);
        case "todo":
            return new AddCommand(restOfCommand);
        case "deadline":
            return new AddCommand(restOfCommand);
        case "event":
            return new AddCommand(restOfCommand);
        case "delete":
            return new DeleteCommand(restOfCommand);
        default:
            return new InvalidCommand(restOfCommand);
        }
    }
}
