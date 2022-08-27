public class Parser {
    public static Command parse(String userCommand) throws UwuException {
            if (userCommand.equals("bye")) {
                return new ExitCommand();
            } else if (userCommand.trim().equals("list")) {
                return new ListCommand();
            } else if (userCommand.startsWith("mark ") || userCommand.startsWith("unmark ")) {
                return new MarkCommand(userCommand);
            } else if (userCommand.startsWith("todo ") ||
                        userCommand.startsWith("deadline ") ||
                        userCommand.startsWith("event ")) {
                return new AddCommand(userCommand);
            } else if (userCommand.startsWith("delete ")) {
                return new DeleteCommand(userCommand);
            } else {
                throw new UnknownCommandException("\n\tsorry >< \n\ti don't know what that means TT");
            }
    }
}
