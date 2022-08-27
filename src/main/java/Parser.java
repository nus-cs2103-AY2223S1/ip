import java.io.IOException;

public class Parser {
    public static Command parse(String inputString) throws KirbyInvalidCommandException {

        switch (inputString.split(" ")[0]) {
        case "todo":
            return new ToDoCommand(inputString);

        case "event":
            return new EventCommand(inputString);

        case "deadline":
            return new DeadlineCommand(inputString);

        case "mark":
            return new MarkCommand(inputString);

        case "unmark":
            return new UnmarkCommand(inputString);

        case "delete":
            return new DeleteCommand(inputString);

        case "bye":
            return new ExitCommand();

        case "list":
            return new ShowListCommand();

        case "get":
            return new GetCommand(inputString);

        default:
            throw new KirbyInvalidCommandException();
        }
    }
}
