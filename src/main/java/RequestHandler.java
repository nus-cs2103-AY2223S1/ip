import command.Command;

public class RequestHandler {
    private enum CommandType {
        LIST, TODO, EVENT, DEADLINE, MARK, UNMARK, DELETE, BYE, INVALID
    }

    public static Command handleRequest(String command) throws InvalidInputException, IncompleteInputException,
            InvalidCommandException {
        String[] commandSplit = command.trim().split(" ", 2);
        CommandType commandType = CommandType.valueOf(commandSplit[0].toUpperCase());

    }



}
