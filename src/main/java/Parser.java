import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseInput(String userInput) throws DukeException {
        String[] split = userInput.split(" ");
        if (userInput.equals("bye") && split.length == 1) {
            return new ByeCommand();
        }

        if (userInput.equals("list") && split.length == 1) {
            return new ListCommand();
        }

        if (split[0].equals("mark") && split.length == 2 && isNumeric(split[1])) {
            return new MarkCommand(Integer.parseInt(split[1]));
        }

        if (split[0].equals("unmark") && split.length == 2 && isNumeric(split[1])) {
            return new UnmarkCommand(Integer.parseInt(split[1]));
        }

        if (split[0].equals("delete") && split.length == 2 && isNumeric(split[1])) {
            return new DeleteCommand(Integer.parseInt(split[1]));
        }

        if (split[0].equals("todo")) {
            String description = userInput.substring(4).trim();
            if (description.length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty :-(");
            }
            return new ToDoCommand(description);
        }

        try {
            if (split[0].equals("deadline") && userInput.contains("/by")) {
                int index = userInput.indexOf("/by");
                String description = userInput.substring(8, index).trim();
                if (description.length() == 0) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty :-(");
                }
                return new DeadlineCommand(description, LocalDate.parse(userInput.substring(index + 3).trim()));
            }

            if (split[0].equals("event") && userInput.contains("/at")) {
                int index = userInput.indexOf("/at");
                String description = userInput.substring(5, index).trim();
                if (description.length() == 0) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty :-(");
                }
                return new EventCommand(description, LocalDate.parse(userInput.substring(index + 3).trim()));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please format date in YYYY-MM-DD.");
        }

        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }
}
