package duke.main;

import duke.command.*;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingTimeException;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;

public class Parser {

    public static Command parse(String fullCommand)
            throws MissingIndexException, MissingDescriptionException, MissingTimeException {
        String[] splitCommand = fullCommand.split(" ", 2);
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ShowListCommand();
        case "mark":
            return makeMarkTaskCommand(true, splitCommand);
        case "unmark":
            return makeMarkTaskCommand(false, splitCommand);
        case "todo":
            return makeMakeToDoTaskCommand(splitCommand);
        case "deadline":
            return makeMakeDeadlineCommand(splitCommand);
        case "event":
            return makeMakeEventCommand(splitCommand);
        case "delete":
            return makeDeleteTaskCommand(splitCommand);
        default:
            return new InvalidCommand();
        }
    }

    private static MarkTaskCommand makeMarkTaskCommand(boolean isDone, String[] command) throws MissingIndexException {
        if (command.length < 2) {
            throw new MissingIndexException("Missing index");
        }
        int index = Integer.parseInt(command[1]);
        return new MarkTaskCommand(isDone, index - 1);
    }

    private static DeleteTaskCommand makeDeleteTaskCommand(String[] command) throws MissingIndexException {
        if (command.length < 2) {
            throw new MissingIndexException("Missing index");
        }
        int index = Integer.parseInt(command[1]);
        return new DeleteTaskCommand(index - 1);
    }

    private static MakeToDoTaskCommand makeMakeToDoTaskCommand(String[] command) throws MissingDescriptionException {
        if (command.length < 2) {
            throw new MissingDescriptionException("Missing description");
        }
        return new MakeToDoTaskCommand(command[1]);
    }

    private static MakeDeadlineCommand makeMakeDeadlineCommand(String[] command)
            throws MissingDescriptionException, MissingTimeException {
        if (command.length < 2) {
            throw new MissingDescriptionException("Missing description");
        }
        String[] splitCommand = command[1].split(Deadline.DELIMITER, 2);
        if (splitCommand.length < 2) {
            throw new MissingTimeException("Missing time");
        }
        return new MakeDeadlineCommand(splitCommand[0], LocalDate.parse(splitCommand[1]));
    }

    private static MakeEventCommand makeMakeEventCommand(String[] command)
            throws MissingDescriptionException, MissingTimeException {
        if (command.length < 2) {
            throw new MissingDescriptionException("Missing description");
        }
        String[] splitCommand = command[1].split(Event.DELIMITER, 2);
        if (splitCommand.length < 2) {
            throw new MissingTimeException("Missing time");
        }
        return new MakeEventCommand(splitCommand[0], LocalDate.parse(splitCommand[1]));
    }
}
