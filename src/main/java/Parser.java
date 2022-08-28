import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        Command successfulCommand = parseHelper(input);
        return successfulCommand;
    }

    private static Command parseHelper(String input) throws DukeException {
        CommandWords commandWord = getCommand(input);
        switch (commandWord) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(getIndex(4, input));
            case UNMARK:
                return new UnmarkCommand(getIndex(6, input));
            case DELETE:
                return new DeleteCommand(getIndex(6, input));
            case TODO:
                if (input.length() < 6) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                if (!input.substring(4, 5).equals(" ")) {
                    throw new DukeException("Command and description should be separated by a space.");
                }
                Task newTask = new Todo(input.substring(5));
                return new AddCommand(newTask);
            case EVENT:
                String[] eventDescDate = parseDescDate("/at", "event", input);
                return new AddCommand(new Event(eventDescDate[0], parseDate(eventDescDate[1])));
            case DEADLINE:
                String[] deadlineDescDate = parseDescDate("/by", "deadline", input);
                return new AddCommand(new Deadline(deadlineDescDate[0], parseDate(deadlineDescDate[1])));
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int getIndex(int commandLength, String input) throws DukeException {
        if (input.length() == commandLength || (input.length() == commandLength + 1 && input.substring(commandLength, commandLength + 1).equals(" "))) {
            throw new DukeException("Task number cannot be empty.");
        }
        if (input.length() > commandLength && !input.substring(commandLength, commandLength + 1).equals(" ")) {
            throw new DukeException("Command and task number should be separated by a space.");
        }
        try {
            int index = Integer.parseInt(input.substring(commandLength + 1)) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number invalid.");
        }
    }
    private static CommandWords getCommand(String input) throws DukeException {
        if (input.equals("bye")) {
            return CommandWords.BYE;
        } else if (input.equals("list")) {
            return CommandWords.LIST;
        } else if (input.length() > 3 && input.substring(0, 4).equals("mark")) {
            return CommandWords.MARK;
        } else if (input.length() > 5 && input.substring(0, 6).equals("unmark")) {
            return CommandWords.UNMARK;
        } else if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
            return CommandWords.TODO;
        } else if (input.length() > 7 && input.substring(0, 8).equals("deadline")) {
            return CommandWords.DEADLINE;
        } else if (input.length() > 4 && input.substring(0, 5).equals("event")) {
            return CommandWords.EVENT;
        } else if (input.length() > 5 && input.substring(0, 6).equals("delete")) {
            return CommandWords.DELETE;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String[] parseDescDate(String parser, String command, String input) throws DukeException{
        int commandLength = command.length();
        if (input.length() < commandLength + 2) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }
        if (!input.substring(commandLength, commandLength + 1).equals(" ")) {
            throw new DukeException("Command and description should be separated by a space.");
        }
        String[] descDate = input.split(" " + parser + " ");
        if (descDate.length < 2) {
            throw new DukeException(command + " date cannot be empty.");
        }
        if (descDate.length > 2) {
            throw new DukeException("More than one " + command + " date entered.");
        }
        return descDate;
    }

    private static LocalDateTime parseDate(String dateTime) throws DukeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException exp) {
            throw new DukeException("Date and time of wrong format.");
        }
    }

}

