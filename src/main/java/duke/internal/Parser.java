package duke.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.command.AliasAddCommand;
import duke.command.AliasListCommand;
import duke.command.AliasRemoveCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * A parser object can parse a command from a string,
 * and determine which Command object should be used to execute it.
 */
public class Parser {
    private static final Set<String> DEFAULT_COMMANDS = Set.of(
            "bye",
            "todo",
            "deadline",
            "event",
            "list",
            "find",
            "mark",
            "unmark",
            "delete",
            "alias"
    );
    private static final Map<String, String> DEFAULT_ALIASES = Map.of("t",
            "todo",
            "d",
            "deadline",
            "e",
            "event",
            "m",
            "mark",
            "u",
            "unmark",
            "um",
            "unmark",
            "f",
            "find",
            "search",
            "find",
            "rm",
            "delete",
            "ls",
            "list"
    );
    private final Map<String, String> aliases = new HashMap<>(DEFAULT_ALIASES);

    /**
     * Splits a string into an array of arguments, delimited by spaces.
     *
     * @param command the string to split
     * @return the array of arguments
     */
    private static String[] getArguments(String command) {
        if (command.isBlank()) {
            return new String[0];
        }
        return command.split(" ");
    }

    private static ByeCommand getByeCommand() {
        return new ByeCommand();
    }

    private static TodoCommand getTodoCommand(String[] arguments) {
        if (arguments.length < 2) {
            throw new DukeException("I need a description for your todo!\nUsage: todo <description>");
        }
        return new TodoCommand(Parser.concatenateArguments(arguments, 1));
    }

    private static DeadlineCommand getDeadlineCommand(String[] arguments) {
        if (arguments.length < 3) {
            throw new DukeException("I need a description and and datetime for your deadline!\n"
                    + "Usage: deadline <description> /by <deadline>");
        }
        // Find the "/by" delimiter to get the two arguments.
        int delimiter = Parser.findArgumentIndex(arguments, "/by");
        assert delimiter > 0 : "/by should be present";
        return new DeadlineCommand(Parser.concatenateArguments(arguments, 1, delimiter),
                Parser.concatenateArguments(arguments, delimiter + 1)
        );
    }

    private static EventCommand getEventCommand(String[] arguments) {
        int delimiter;
        if (arguments.length < 3) {
            throw new DukeException("I need a description and and datetime for your event!\n"
                    + "Usage: event <description> /at <datetime>");
        }
        delimiter = Parser.findArgumentIndex(arguments, "/at");
        assert delimiter > 0 : "/at should be present";
        return new EventCommand(Parser.concatenateArguments(arguments, 1, delimiter),
                Parser.concatenateArguments(arguments, delimiter + 1)
        );
    }

    private static ListCommand getListCommand() {
        return new ListCommand();
    }

    private static FindCommand getFindCommand(String[] arguments) {
        if (arguments.length < 2) {
            throw new DukeException("I need the search term!\nUsage: find <search term>");
        }
        return new FindCommand(Parser.concatenateArguments(arguments, 1));
    }

    private static MarkCommand getMarkCommand(String[] arguments) {
        if (arguments.length < 2) {
            throw new DukeException("I need the task number!\nUsage: mark <task number>");
        }
        return new MarkCommand(Integer.parseInt(arguments[1]) - 1);
    }

    private static UnmarkCommand getUnmarkCommand(String[] arguments) {
        if (arguments.length < 2) {
            throw new DukeException("I need the task number!\nUsage: unmark <task number>");
        }
        return new UnmarkCommand(Integer.parseInt(arguments[1]) - 1);
    }

    private static DeleteCommand getDeleteCommand(String[] arguments) {
        if (arguments.length < 2) {
            throw new DukeException("I need the task number!\nUsage: delete <task number>");
        }
        return new DeleteCommand(Integer.parseInt(arguments[1]) - 1);
    }

    /**
     * Returns the index of the first occurrence of a search string in an array of arguments.
     *
     * @param arguments the array of arguments
     * @param query     the search string
     * @return the index of the first occurrence of the search string
     * @throws DukeException if the search string is not found
     */
    private static int findArgumentIndex(String[] arguments, String query) {
        return IntStream.range(0, arguments.length)
                .filter(i -> arguments[i].equals(query))
                .findFirst()
                .orElseThrow(() -> new DukeException(String.format("Missing argument `%s`.",
                        query
                )));
    }

    /**
     * Parses a command from a string.
     *
     * @param str the string to parse
     * @return the Command object to execute
     */
    public Command parseString(String str) {
        String[] arguments = Parser.getArguments(str);
        if (arguments.length == 0) {
            throw new DukeException("Give me a command!");
        }
        switch (arguments[0]) {
        case "bye":
            return getByeCommand();
        case "todo":
            return getTodoCommand(arguments);
        case "deadline":
            return getDeadlineCommand(arguments);
        case "event":
            return getEventCommand(arguments);
        case "list":
            return getListCommand();
        case "find":
            return getFindCommand(arguments);
        case "mark":
            return getMarkCommand(arguments);
        case "unmark":
            return getUnmarkCommand(arguments);
        case "delete":
            return getDeleteCommand(arguments);
        case "alias":
            return getAliasCommand(arguments);
        default:
            return expandAliasedCommand(arguments);
        }
    }

    /**
     * Adds an alias (shorthand command) to a command.
     * Binding an alias to another alias is not allowed to prevent infinite recursion
     * when expanding aliases during command parsing.
     *
     * @param alias   the alias to add
     * @param command the command to bind the alias to
     */
    public void addAlias(String alias, String command) {
        if (alias.equals(command)) {
            throw new DukeException("Sorry! I can't bind an alias to itself.");
        }
        if (DEFAULT_COMMANDS.contains(alias)) {
            throw new DukeException("Sorry! I can't bind a command as an alias.");
        }
        if (!DEFAULT_COMMANDS.contains(command)) {
            // This prevents any circular aliases, because aliases can only be bound to commands.
            if (aliases.containsKey(command)) {
                throw new DukeException(
                        "Sorry! I can't bind an alias to existing aliases to prevent circular aliases.");
            }
            throw new DukeException(String.format("The command `%s` does not exist!", command));
        }
        if (aliases.containsKey(alias)) {
            throw new DukeException(String.format("The alias `%s` already exists!", alias));
        }
        aliases.put(alias, command);
    }

    /**
     * Removes an alias bound to a command if it exists.
     *
     * @param alias the alias to remove
     * @return the command the alias was bound to
     * @throws DukeException if the alias does not exist
     */
    public String removeAlias(String alias) {
        if (!aliases.containsKey(alias)) {
            throw new DukeException("That alias does not exist!");
        }
        String command = aliases.get(alias);
        aliases.remove(alias);
        return command;
    }

    private Command getAliasCommand(String[] arguments) {
        if (arguments.length < 2) {
            throw new DukeException("Usage: alias [add|remove|list] [alias] [command]");
        }
        switch (arguments[1]) {
        case "add":
            if (arguments.length != 4) {
                throw new DukeException(
                        "Invalid number of arguments. Usage: alias add [alias] [command]");
            }
            return new AliasAddCommand(arguments[2], arguments[3]);
        case "remove":
            if (arguments.length != 3) {
                throw new DukeException("Invalid number of arguments. Usage: alias remove [alias]");
            }
            return new AliasRemoveCommand(arguments[2]);
        case "list":
            if (arguments.length != 2) {
                throw new DukeException("Invalid number of arguments. Usage: alias list");
            }
            String allAliases = this.aliases.entrySet()
                    .stream()
                    .map(entry -> String.format("%s = %s\n", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining());
            return new AliasListCommand(allAliases);
        default:
            throw new DukeException(String.format(
                    "Invalid argument `%s`. Usage: alias [add|remove|list] [alias] [command]",
                    arguments[1]
            ));
        }
    }

    /**
     * Returns a de-aliased command to execute.
     * For example, it expands `m 1` to `mark 1`.
     *
     * @return the Command object to execute
     */
    private Command expandAliasedCommand(String[] arguments) {
        String alias = arguments[0];
        if (!aliases.containsKey(alias)) {
            throw new DukeException("Sorry! I didn't understand that command!");
        }
        String command = String.format("%s %s",
                aliases.get(alias),
                Parser.concatenateArguments(arguments, 1)
        );
        return parseString(command);
    }

    /**
     * Joins an array of arguments into a single string, with spaces between them.
     *
     * @param arguments the array of arguments
     * @param start the index of the first argument (inclusive)
     * @param end the end index of the last argument (exclusive)
     * @return the joined string
     */
    private static String concatenateArguments(String[] arguments, int start, int end) {
        return Arrays.stream(arguments, start, end)
                .map(str -> str + " ")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * Joins an array of arguments into a single string, with spaces between them.
     * Overloaded variant which concatenates all arguments from the start index,
     * to the end of the array.
     *
     * @param arguments the array of arguments
     * @param start the index of the first argument (inclusive)
     * @return the joined string
     */
    private static String concatenateArguments(String[] arguments, int start) {
        return Arrays.stream(arguments, start, arguments.length)
                .map(str -> str + " ")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .strip();
    }
}
