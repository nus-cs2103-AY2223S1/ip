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
        if (split[0].equals("note")) {
            return generateNoteCommand(userInput);
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
        int byIndex = userInput.indexOf("/by");
        int noteIndex = userInput.indexOf("/note");
        String description = userInput.substring(8, byIndex).trim();
        String note = userInput.substring(noteIndex + 5);
        CharSequence dateText = userInput.substring(byIndex + 3).trim();
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty :/");
        } else {
            assert description.length() > 0 : "Description length should not be 0.";
        }
        if (noteIndex > byIndex){
            dateText = userInput.substring(byIndex + 3, noteIndex).trim();
            return new DeadlineCommand(description, LocalDate.parse(dateText), note);
        } else {
            return new DeadlineCommand(description, LocalDate.parse(dateText));
        }
    }

    public static Command generateEventCommand(String userInput) throws DukeException {
        int atIndex = userInput.indexOf("/at");
        int noteIndex = userInput.indexOf("/note");
        String description = userInput.substring(5, atIndex).trim();
        String note = userInput.substring(noteIndex + 5);
        CharSequence dateText = userInput.substring(atIndex + 3).trim();
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty :/");
        } else {
            assert description.length() > 0 : "Description length should not be 0.";
        }
        if (noteIndex > atIndex) {
            dateText = userInput.substring(atIndex + 3, noteIndex).trim();
            return new EventCommand(description, LocalDate.parse(dateText), note);
        } else {
            return new EventCommand(description, LocalDate.parse(dateText));
        }
    }

    public static Command generateToDoCommand(String userInput) throws DukeException {
        int noteIndex = userInput.indexOf("/note");
        String description = userInput.substring(4).trim();
        if (description.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty :/");
        } else {
            assert description.length() > 0 : "Description length should not be 0.";
        }
        if (noteIndex > 1) {
            description = userInput.substring(4, noteIndex).trim();
            String note = userInput.substring(noteIndex + 5);
            return new ToDoCommand(description, note);
        } else {
            return new ToDoCommand(description);
        }
    }

    public static Command generateNoteCommand(String userInput) {
        int taskIndex = userInput.indexOf("/task");
        int editIndex = userInput.indexOf("/edit");
        int deleteIndex = userInput.indexOf("/delete");
        if (editIndex > 1) {
            String newNote = userInput.substring(editIndex + 6);
            int taskNo = Integer.parseInt(userInput.substring(taskIndex + 6, editIndex - 1)) - 1;
            return new EditNoteCommand(taskNo, newNote);
        } else {
            int taskNo = Integer.parseInt(userInput.substring(taskIndex + 6, deleteIndex - 1)) - 1;
            return new DeleteNoteCommand(taskNo);
        }

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
