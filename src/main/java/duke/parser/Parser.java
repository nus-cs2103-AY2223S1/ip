package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    /**
     * Parses the given user's string of command input and
     * returns the corresponding command object.
     *
     * @param input the user input to parse
     * @return the corresponding command object
     * @throws DukeException if the command is unrecognised
     */
    public static Command parse(String input) throws DukeException {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
        String firstWord = words.remove(0);
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
        default:
            return new InvalidCommand();
        }
    }
}