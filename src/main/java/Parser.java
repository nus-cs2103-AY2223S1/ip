import java.util.Scanner;

public class Parser {
    public static Command parseUserInput(Scanner userInput) {
        UserCommandList command;
        try {
            command = UserCommandList.valueOf(userInput.next().toUpperCase());
        } catch (IllegalArgumentException iae) {
            return new IncorrectCommand(userInput);
        }
        switch(command) {
        case TODO:
            return new ToDoCommand(userInput);
        case DEADLINE:
            return new DeadlineCommand(userInput);
        case EVENT:
            return new EventCommand(userInput);
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(userInput);
        case UNMARK:
            return new UnmarkCommand(userInput);
        case DELETE:
            return new DeleteCommand(userInput);
        case BYE:
            return new ByeCommand();
        default:
            throw new AssertionError("Should not reach this stage.");
        }
    }
}
