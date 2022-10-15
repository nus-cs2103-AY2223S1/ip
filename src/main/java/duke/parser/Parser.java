package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import duke.commands.AliasCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnaliasCommand;
import duke.commands.UnknownCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.storage.Storage;

public class Parser {

    private final HashMap<String, String> aliases;
    private final Storage storage;

    public Parser(Storage storage) {
        this.storage = storage;
        this.aliases = storage.loadAliases();
    }

    /**
     * Add an alias for a command.
     *
     * @param alias The alias.
     * @param command The aliased command.
     */
    public void addAlias(String alias, String command) {
        this.aliases.put(alias, command);
        this.storage.updateAlias(aliases);
    }

    /**
     * Remove an alias for a command.
     *
     * @param alias The alias.
     */
    public void removeAlias(String alias) {
        if (aliases.remove(alias) != null) {
            storage.updateAlias(aliases);
        }
    }

    /**
     * Parses the provided user input to return the command to be executed.
     *
     * @param input The provided user input.
     * @return The command the user wants to execute.
     * @throws DukeException if the command has invalid arguments.
     */
    public Command parse(String input) throws DukeException {
        int idx = input.indexOf(' ');
        String textCommand = idx < 0 ? input : input.substring(0, idx);

        textCommand = this.aliases.getOrDefault(textCommand, textCommand);

        if (idx != -1) {
            input = textCommand + input.substring(idx);
        }

        switch (textCommand) {
        case "bye":
            return new ByeCommand();

        case "todo":
            return parseTodo(input);

        case "deadline":
            return parseDeadline(input);

        case "event":
            return parseEvent(input);

        case "list":
            return new ListCommand();

        case "mark":
            return parseMark(input);

        case "unmark":
            return parseUnmark(input);

        case "delete":
            return parseDelete(input);

        case "find":
            return parseFind(input);

        case "alias":
            return parseAlias(input);

        case "unalias":
            return parseUnalias(input);

        default:
            return new UnknownCommand();
        }
    }

    private TodoCommand parseTodo(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Invalid todo command. Example: todo borrow book");
        }
        return new TodoCommand(input.substring(5));
    }

    private DeadlineCommand parseDeadline(String input) throws DukeException {
        int idx = input.indexOf("/by");

        if (idx < 0 || idx == 9 || input.length() <= idx + 5) {
            throw new DukeException("Invalid deadline command. Example: deadline return book /by 2022-04-02");
        }

        String description = input.substring(9, idx - 1);

        try {
            LocalDate by = LocalDate.parse(input.substring(idx + 4));
            return new DeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Example: deadline return book /by 2022-04-02");
        }

    }

    private EventCommand parseEvent(String input) throws DukeException {
        int idx = input.indexOf("/at");

        if (idx < 0 || idx == 6 || input.length() <= idx + 5) {
            throw new DukeException("Invalid deadline command. Example: event project meeting /at 2020-02-29");
        }

        String description = input.substring(6, idx - 1);

        try {
            LocalDate at = LocalDate.parse(input.substring(idx + 4));
            return new EventCommand(description, at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Example: event project meeting /at 2020-02-29");
        }

    }

    private MarkCommand parseMark(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: 'mark n'");
        }

        try {
            // Get the index of the task to mark as complete
            int idx = Integer.parseInt(components[1]);
            return new MarkCommand(idx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
    }

    private UnmarkCommand parseUnmark(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: 'unmark n'");
        }

        try {
            // Get the index of the task to unmark
            int idx = Integer.parseInt(components[1]);
            return new UnmarkCommand(idx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
    }

    private DeleteCommand parseDelete(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: 'delete n'");
        }

        try {
            // Get the index of the task to delete
            int idx = Integer.parseInt(components[1]);
            return new DeleteCommand(idx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
    }

    private FindCommand parseFind(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Usage: find keyword");
        }
        return new FindCommand(input.substring(5));
    }

    private AliasCommand parseAlias(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 3) {
            throw new DukeException("Usage: alias <alias> <command>");
        }

        if (components[1].equals("alias")) {
            throw new DukeException("The alias command cannot be renamed :(");
        }

        return new AliasCommand(components[1], components[2], this);
    }

    private UnaliasCommand parseUnalias(String input) throws DukeException {
        String[] components = input.split(" ");

        if (components.length != 2) {
            throw new DukeException("Usage: unalias <alias>");
        }

        return new UnaliasCommand(components[1], this);
    }
}
