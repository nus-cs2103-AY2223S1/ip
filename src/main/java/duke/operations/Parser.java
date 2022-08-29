package duke.operations;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.MakeTodoCommand;
import duke.command.MakeDeadlineCommand;
import duke.command.MakeEventCommand;
import duke.command.InvalidCommand;

public class Parser {

    public static Command parse(String cmd) {
        cmd = cmd.strip();
        int separator = cmd.indexOf(' ');
        String keyWord = cmd;
        if (separator != -1) {
            keyWord = cmd.substring(0, separator);
        }
        switch (keyWord) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                return new MarkCommand(cmd);
            case "unmark":
                return new UnmarkCommand(cmd);
            case "delete":
                return new DeleteCommand(cmd);
            case "todo":
                return new MakeTodoCommand(cmd.substring("todo".length()));
            case "deadline":
                return new MakeDeadlineCommand(cmd.substring("deadline".length()));
            case "event":
                return new MakeEventCommand(cmd.substring("event".length()));
            default:
                return new InvalidCommand();
        }
    }
}
