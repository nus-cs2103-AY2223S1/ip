package duke.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.command.AllCommand;
import duke.command.AnyCommand;
import duke.command.AtCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;

/**
 * Parser for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class Parser {
    private boolean isListening;

    /**
     * Constructs a new instance of Parser.
     */
    public Parser() {
        this.isListening = true;
    }

    /**
     * Parses the given string to a Command object.
     *
     * @param text the string to be parsed.
     * @return the Command which represents the corresponding string input.
     * @throws DukeException If the string to be parsed is not valid.
     */
    public Command parseText(String text) throws DukeException {
        List<String> commands = Arrays.stream(text.trim().toLowerCase().split(" ", 2))
                .map(String::trim)
                .collect(Collectors.toList());

        String mainCommand = commands.get(0);
        String description = commands.size() > 1 ? commands.get(1) : "";

        switch (mainCommand) {
        case "bye":
            this.isListening = false;
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(description);

        case "unmark":
            return new UnmarkCommand(description);

        case "delete":
            return new DeleteCommand(description);

        case "todo":
            return new ToDoCommand(description);

        case "deadline":
            return new DeadlineCommand(description);

        case "event":
            return new EventCommand(description);

        case "find":
            return new FindCommand(description);

        case "at":
            return new AtCommand(description);

        case "any":
            return new AnyCommand(description);

        case "all":
            return new AllCommand(description);

        case "":
            throw new EmptyCommandException();

        default:
            throw new DukeException();
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
