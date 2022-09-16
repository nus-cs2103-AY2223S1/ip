package gina;

import gina.commands.Command;
import gina.commands.DeadlineCommand;
import gina.commands.DeleteCommand;
import gina.commands.EventCommand;
import gina.commands.ExitCommand;
import gina.commands.FindCommand;
import gina.commands.ListCommand;
import gina.commands.MarkCommand;
import gina.commands.OnCommand;
import gina.commands.ContactCommand;
import gina.commands.ToDoCommand;
import gina.commands.UnmarkCommand;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses user's input command.
     *
     * @param command The user's input command.
     * @return The command to be done.
     * @throws GinaException If the input is incorrect.
     */
    public static Command parse(String command) throws GinaException {
        command = command.toLowerCase();

        String[] input = command.split(" ", 2);

        switch (input[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            if (input.length < 2) {
                throw new GinaException("Hey! You have to tell me the task number.");
            }
            return new DeleteCommand(input[1]);
        case "todo":
            if (input.length < 2) {
                throw new GinaException("You're doing nothing, huh? Give me a description...");
            }
            return new ToDoCommand(input[1]);
        case "event":
            if (input.length < 2) {
                throw new GinaException("You're doing nothing, huh? Give me a description...");
            }
            return new EventCommand(input[1]);
        case "deadline":
            if (input.length < 2) {
                throw new GinaException("You're doing nothing, huh? Give me a description...");
            }
            return new DeadlineCommand(input[1]);
        case "mark":
            if (input.length < 2) {
                throw new GinaException("I don't read minds :/ Tell me the task number.");
            }
            return new MarkCommand(input[1]);
        case "unmark":
            if (input.length < 2) {
                throw new GinaException("I don't read minds :/ Tell me the task number.");
            }
            return new UnmarkCommand(input[1]);
        case "on":
            if (input.length < 2) {
                throw new GinaException("You have to give me a date to check, you know?");
            }
            return new OnCommand(input[1]);
        case "find":
            if (input.length < 2) {
                return new FindCommand("");
            }
            return new FindCommand(input[1].trim());
        case "contact":
            if (input.length < 2) {
                throw new GinaException("How would I know who you want me to add??"
                        + "\nGive me a name!");
            }
            return new ContactCommand(input[1]);
        default:
            throw new GinaException("What's " + command + " ??\n"
                    + "Say something I can understand!");
        }
    }
}
