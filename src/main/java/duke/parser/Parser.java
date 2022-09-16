package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.FindDeadlinesCommand;
import duke.command.FindTagCommand;
import duke.command.GoodbyeCommand;
import duke.command.HelloCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UntagCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.DukeException;

import java.util.Locale;

public class Parser {

    /**
     * Parser to interpret the commands.
     *
     * @param fullCommand The instructions to execute.
     * @return A command that holds the information as specified by fullCommand.
     * @throws DukeException The exception to be thrown when an error occurs.
     */
    public static Command parse(String fullCommand) throws DukeException {

        String instruction = fullCommand.contains(" ") ? fullCommand.split(" ", 2)[0] : fullCommand;
        String parameters = fullCommand.contains(" ") ? fullCommand.split(" ", 2)[1] : "";
        Command command;

        switch (instruction.toLowerCase(Locale.ROOT)) {

        case "hello":
            command = new HelloCommand();
            break;

        case "bye":
            command = new GoodbyeCommand();
            break;

        case "list":
            command = new ListCommand();
            break;

        case "mark":
            command = new MarkCommand(fullCommand);
            break;

        case "unmark":
            command = new UnmarkCommand(fullCommand);
            break;

        case "deadline":
            command = new DeadlineCommand(parameters);
            break;

        case "event":
            command = new EventCommand(parameters);
            break;

        case "todo":
            command = new TodoCommand(parameters);
            break;

        case "delete":
            command = new DeleteCommand(fullCommand);
            break;

        case "deadlines":
            command = new FindDeadlinesCommand(parameters);
            break;

        case "find":
            command = new FindCommand(parameters);
            break;

        case "tag":
            command = new TagCommand(fullCommand);
            break;

        case "untag":
            command = new UntagCommand(fullCommand);
            break;

        case "findtag":
            command = new FindTagCommand(parameters);
            break;

        default:
            throw new DukeException("Invalid Command!");
        }

        return command;
    }

}
