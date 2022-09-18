package duke.command;

public class Parser {

    private static final String[] acceptedCommands = { "todo", "deadline", "event", "list",
            "delete", "find", "mark", "unmark", "bye"};

    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ");
        if (parts[0].contains(acceptedCommands[0])) {
            String command = fullCommand.replaceFirst(acceptedCommands[0], "");
            return new TaskCommand(acceptedCommands[0], command);
        } else if (parts[0].contains(acceptedCommands[1])) {
            String command = fullCommand.replaceFirst(acceptedCommands[1], "");
            return new TaskCommand(acceptedCommands[1], command);
        } else if (parts[0].contains(acceptedCommands[2])) {
            String command = fullCommand.replaceFirst(acceptedCommands[2], "");
            return new TaskCommand(acceptedCommands[2], command);
        } else if (parts[0].contains(acceptedCommands[3])) {
            return new ListCommand();
        } else if (parts[0].contains(acceptedCommands[4])) {
            String command = fullCommand.replaceFirst("delete ", "");
            return new DeleteCommand(command);
        }  else if (parts[0].contains(acceptedCommands[5])) {
            String command = fullCommand.replaceFirst("find ", "");
            return new FindCommand(command);
        } else if (parts[0].contains(acceptedCommands[6]) && !parts[0].contains(acceptedCommands[7])) {
            String command = fullCommand.replaceFirst("mark ", "");
            return new MarkCommand(command);
        } else if (parts[0].contains(acceptedCommands[7])) {
            String command = fullCommand.replaceFirst("unmark ", "");
            return new UnmarkCommand(command);
        } else if (parts[0].contains(acceptedCommands[8])) {
            return new ExitCommand();
        }
        return new InvalidCommand();
    }
}
