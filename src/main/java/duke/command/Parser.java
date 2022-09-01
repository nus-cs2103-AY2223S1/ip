package duke.command;

public class Parser {

    private static final String[] acceptedCommands = { "list", "mark", "unmark", "delete",
            "todo", "deadline", "event", "bye" };

    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ");
        if (parts[0].contains(acceptedCommands[0])) {
            return new ListCommand();
        } else if (parts[0].contains(acceptedCommands[1]) && !parts[0].contains(acceptedCommands[2])) {
            String command = fullCommand.replaceFirst("mark ", "");
            try {
                int number = Integer.parseInt(command);
                return new MarkCommand(number);
            } catch (NumberFormatException e) {}
        } else if (parts[0].contains(acceptedCommands[2])) {
            String command = fullCommand.replaceFirst("unmark ", "");
            try {
                int number = Integer.parseInt(command);
                return new UnmarkCommand(number);
            } catch (NumberFormatException e) {}
        } else if (parts[0].contains(acceptedCommands[3])) {
            String command = fullCommand.replaceFirst("delete ", "");
            try {
                int number = Integer.parseInt(command);
                return new DeleteCommand(number);
            } catch (NumberFormatException e) {}
        } else if (parts[0].contains(acceptedCommands[4])) {
            String command = fullCommand.replaceFirst(acceptedCommands[4], "");
            return new TaskCommand(acceptedCommands[4], command);
        } else if (parts[0].contains(acceptedCommands[5])) {
            String command = fullCommand.replaceFirst(acceptedCommands[5], "");
            return new TaskCommand(acceptedCommands[5], command);
        } else if (parts[0].contains(acceptedCommands[6])) {
            String command = fullCommand.replaceFirst(acceptedCommands[6], "");
            return new TaskCommand(acceptedCommands[6], command);
        } else if (parts[0].contains(acceptedCommands[7])) {
            return new ExitCommand();
        }
        return new InvalidCommand();
    }
}
