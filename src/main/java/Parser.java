import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {
    public static LocalDateTime parseDateTime(String dateTimeString) {
        String[] dateTimeSplit = dateTimeString.split("[ T]");
        String isoDateFormat = "";
        if (dateTimeSplit.length == 1) {
            isoDateFormat = String.format("%sT23:59",dateTimeSplit[0]);
        } else {
            isoDateFormat = String.format("%sT%s", dateTimeSplit[0], dateTimeSplit[1]);
        }
        return LocalDateTime.parse(isoDateFormat);
    }
 
    private static boolean isDigit(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public static Command parse(String fullCommand) throws DukeException, DateTimeException {
        String[] fullCommandSplit = fullCommand.split(" ", 2);
        String command = fullCommandSplit[0].strip();
        String commandArgument = fullCommandSplit.length > 1 ? fullCommandSplit[1].strip() : "";
        
        if (command.equals("")) {
            throw new DukeException("Command cannot be empty");
        }
        try {
            if (command.equals("todo")) {
                if (commandArgument.length() == 0) {
                    throw new InvalidCommandFormatException(TodoCommand.getFormat());
                }
                return new TodoCommand(commandArgument);
            } else if (command.equals("deadline")) {
                String[] argumentSplit = commandArgument.split(" /by ");
                if (argumentSplit.length < 2) {
                    throw new InvalidCommandFormatException(DeadlineCommand.getFormat());
                }
                String description = argumentSplit[0].strip();
                LocalDateTime byDateTime = parseDateTime(argumentSplit[1].strip());
                return new DeadlineCommand(description, byDateTime);
            } else if (command.equals("event")) {
                String[] argumentSplit = commandArgument.split(" /at ");
                if (argumentSplit.length < 2) {
                    throw new InvalidCommandFormatException(EventCommand.getFormat());
                }
                String description = argumentSplit[0].strip();
                LocalDateTime atDateTime = parseDateTime(argumentSplit[1].strip());
                return new EventCommand(description, atDateTime);
            } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                String commandFormat = command.equals("mark")
                        ? MarkCommand.getFormat()
                        : command.equals("unmark")
                        ? UnmarkCommand.getFormat()
                        : DeleteCommand.getFormat();
                if (commandArgument.equals("") || !isDigit(commandArgument)) {
                    throw new InvalidCommandFormatException(commandFormat);
                }

                int taskIndex = Integer.parseInt(commandArgument) - 1;
                return command.equals("mark")
                        ? new MarkCommand(taskIndex)
                        : command.equals("unmark")
                        ? new UnmarkCommand(taskIndex)
                        : new DeleteCommand(taskIndex);
            } else if (command.equals("bye")) {
                return new ByeCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } 
        } catch (DateTimeException dte) {
            throw new DukeException("Datetime must be in this format: <DATE> <TIME>\n  DATE: YYYY-MM-DD\n  TIME(optional): HH:MM");
        }
        throw new UnknownCommandException(command);
    }
}
