import java.util.Scanner;

public class Parser {
    public static Command parseUserCommand(Scanner sc) throws JarvisException {
        System.out.print("<< ");
        String command = sc.nextLine();
        String[] arguments = command.trim().split(" ");
        switch (arguments[0]) {
        case "bye":
            return new ByeCommand(command);
        case "list":
            return new ListCommand(command);
        case "mark":
        case "unmark":
            return new MarkCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(command);
        default:
            throw new JarvisException("Unrecognised. Please enter a valid command.");
        }
    }
}
