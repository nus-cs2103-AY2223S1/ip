package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public static Command parse(String input) throws DukeException {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
        String firstWord = words.remove(0);
        // String restOfWords = String.join(" ", words);
        switch (firstWord) {
        case "list":
            return new ListCommand(words);
        case "bye":
            return new ExitCommand();
        case "mark":
            return new MarkCommand(words, firstWord);
        case "unmark":
            return new MarkCommand(words, firstWord);
        case "todo":
            return new AddCommand(words, firstWord);
        case "deadline":
            return new AddCommand(words, firstWord);
        case "event":
            return new AddCommand(words, firstWord);
        case "delete":
            return new DeleteCommand(words);
        case "find":
            return new FindCommand(words);
        default:
            return new InvalidCommand();
        }
    }
}
