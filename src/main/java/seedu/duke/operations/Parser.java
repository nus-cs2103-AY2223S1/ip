package seedu.duke.operations;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.UnmarkCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.MakeTodoCommand;
import seedu.duke.command.MakeDeadlineCommand;
import seedu.duke.command.MakeEventCommand;
import seedu.duke.command.InvalidCommand;

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
