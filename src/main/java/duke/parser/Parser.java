package duke.parser;

import java.util.Arrays;
import java.util.List;

import duke.exception.DukeException;
import duke.loanbook.command.AddContactCommand;
import duke.loanbook.command.DeleteContactCommand;
import duke.loanbook.command.ListContactsCommand;
import duke.loanbook.command.LoanbookCommand;
import duke.task.command.AddCommand;
import duke.task.command.Command;
import duke.task.command.DeleteCommand;
import duke.task.command.ExitCommand;
import duke.task.command.FindCommand;
import duke.task.command.HelpCommand;
import duke.task.command.ListCommand;
import duke.task.command.MarkCommand;
import duke.task.command.UnmarkCommand;

/**
 * Parser class that deals with making sense of the user command.
 *
 * @author Elgin
 */
public class Parser {
    /**
     * Parses the user input and return the command that the user is demanding.
     *
     * @param userInput The user input into the CLI.
     * @return Command that the user wants to execute.
     */
    public static Command parse(String userInput) {
        if (Parser.isWrongDukeUsage(userInput)) {
            throw new DukeException(Parser.getErrorMessage(userInput));
        }

        // e.g. list, event, deadline, mark, delete.
        String userCommand = Parser.getUserCommand(userInput);

        // prefix of the userCommand.
        String arguments = Parser.getArguments(userInput, userCommand);

        return Parser.getTaskCommand(userCommand, arguments);
    }

    /**
     * Parses the user input for Loanbook commands.
     *
     */
    public static LoanbookCommand parseLoanbookCommand(String userInput) {
        if (Parser.isWrongDukeUsage(userInput)) {
            throw new DukeException(Parser.getErrorMessage(userInput));
        }

        // e.g. list, event, deadline, mark, delete.
        String userCommand = Parser.getUserCommand(userInput);

        // prefix of the userCommand.
        String arguments = Parser.getArguments(userInput, userCommand);

        return Parser.getLoanbookCommand(userCommand, arguments);
    }

    /**
     * Checks if user is using duke commands wrongly, i.e. using commands that require
     * arguments without arguments.
     *
     * @param userInput What the user has keyed in.
     * @return True if duke command is used correctly, false otherwise.
     */
    private static boolean isWrongDukeUsage(String userInput) {
        String[] invalidCommands = new String[]{
            "todo",
            "event",
            "deadline",
            "mark",
            "unmark",
            "find",
            "delete",
            "loanbook",
            "loanbook delete",
            "loanbook add"
        };

        // Convert String array to list
        List<String> invalidList = Arrays.asList(invalidCommands);

        return invalidList.contains(userInput);
    }

    /**
     * Gets the error message for why the user is using Duke command wrongly.
     *
     * @param userInput What the user has keyed in.
     * @return String representing the error message due to wrong usage.
     */
    private static String getErrorMessage(String userInput) {
        switch(userInput) {
        case "todo":
        case "deadline":
        case "event":
            return "The description of a task cannot be empty!";
        case "mark":
            return "Usage 'mark index'";
        case "unmark":
            return "Usage 'unmark index'";
        case "delete":
            return "Usage 'delete index'";
        case "find":
            return "Usage 'find title'";
        case "loanbook":
            return "Wrong usage of loanbook!";
        case "loanbook add":
            return "Usage 'loanbook add name phone amount isOwe(true/false)";
        case "loanbook delete":
            return "Usage 'loanbook delete name'";
        default:
            return "There is no error in this user input OR there are no error message for this error!";
        }
    }

    /**
     * Gets the command that the user has keyed in (e.g. deadline, list, event, mark, unmark).
     *
     * @param userInput What the user has keyed in.
     * @return String representation of the command.
     */
    private static String getUserCommand(String userInput) {
        String[] inputWords = userInput.trim().split(" ");

        // Because loanbook commands are 2 words, we need to concatenate them.
        return inputWords[0].equals("loanbook") && inputWords.length != 1
                ? inputWords[0] + " " + inputWords[1]
                : inputWords[0];
    }

    /**
     * Gets the prefix after the user command (deadline, list, event, mark, unmark).
     *
     * @param userInput What the user has keyed in.
     * @param userCommand The command (e.g. deadline, list, event, mark, unmark).
     * @return String representation of the prefix of userCommand.
     */
    private static String getArguments(String userInput, String userCommand) {
        return userInput.substring(userCommand.length()).trim();
    }

    /**
     * Checks whether the command the user wants to execute is a Task command.
     *
     * @param userInput What the user has keyed in.
     * @return True if it is a Task command, false otherwise.
     */
    public static boolean isTaskCommand(String userInput) {
        return !Parser.getUserCommand(userInput).startsWith("loanbook");
    }

    /**
     * Gets the Task Command that the user is trying to execute.
     *
     * @param userCommand The command (e.g. deadline, list, event, mark, unmark).
     * @param arguments Prefix of the command.
     * @return Command that can be executed to create a result.
     */
    public static Command getTaskCommand(String userCommand, String arguments) {
        switch (userCommand) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(userCommand, arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("I'm sorry, I don't understand what that means :-(");
        }
    }

    /**
     * Gets the LoanbookCommand that the user is intending to execute.
     *
     * @param userCommand The user command (e.g. loanbook add, loanbook delete).
     * @param arguments The String preceding the user command.
     * @return LoanbookCommand that can be executed.
     */
    public static LoanbookCommand getLoanbookCommand(String userCommand, String arguments) {
        switch (userCommand) {
        case "loanbook add":
            return new AddContactCommand(arguments.split(" "));
        case "loanbook list":
            return new ListContactsCommand();
        case "loanbook delete":
            return new DeleteContactCommand(arguments);
        default:
            throw new DukeException("I'm sorry, I don't understand what that means :-(");
        }
    }
}
