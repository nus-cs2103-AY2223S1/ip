package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDate;

/**
 * The class for a Parser, which is used to parse the String input into a Command.
 *
 * @author kaij77
 * @version 0.1
 */
public class Parser {
    /**
     * Parses the String input into a Command.
     *
     * @param userInput The String input given by the user
     * @return The Command resulting from the input
     * @throws DukeException
     */
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
        if (split[0].equals("find")) {
            String searchTerm = "";
            for (int i = 1; i < split.length; i++) {
                searchTerm = searchTerm + " " + split[i];
            }
            return new FindCommand(searchTerm);
        }
        if (split[0].equals("todo")) {
            return generateToDoCommand(userInput);
        }
        if (split[0].equals("deadline") && userInput.contains("/by")) {
            return generateDeadlineCommand(userInput);
        }
        if (split[0].equals("event") && userInput.contains("/at")) {
            return generateEventCommand(userInput);
        }
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :/");
    }

    public static Command generateDeadlineCommand(String userInput) throws DukeException {
        int index = userInput.indexOf("/by");
        String description = userInput.substring(8, index).trim();
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty :/");
        }
        return new DeadlineCommand(description, LocalDate.parse(userInput.substring(index + 3).trim()));
    }

    public static Command generateEventCommand(String userInput) throws DukeException {
        int index = userInput.indexOf("/at");
        String description = userInput.substring(5, index).trim();
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty :/");
        }
        return new EventCommand(description, LocalDate.parse(userInput.substring(index + 3).trim()));
    }

    public static Command generateToDoCommand(String userInput) throws DukeException {
        String description = userInput.substring(4).trim();
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty :/");
        }
        return new ToDoCommand(description);
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
