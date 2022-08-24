package duke.utils;

import duke.Date;
import duke.DukeException;
import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Handles the parsing of dates for Deadlines/Events
     * @param input String input of date
     * @return duke.Date object used to construct duke.task.Deadline/duke.task.Event
     * @throws DukeException Exception if date format is wrong
     */
    public static Date parseDate(String input) throws DukeException{
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsed = LocalDate.parse(input, dtf);

            return new Date(parsed);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please follow the duke.Date and Time Format: yyyy-MM-dd [2000-01-01]");
        }

    }

    /**
     * Handles the parsing of dates for Deadlines/Events
     * @param input String input of date
     * @return duke.Date object used to construct duke.task.Deadline/duke.task.Event
     * @throws DukeException Exception if date format is wrong
     */
    public static Date parseDateSave(String input) throws DukeException{
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate parsed = LocalDate.parse(input, dtf);
            return new Date(parsed);

        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The save file is corrupted.");
        }

    }

    public static Command parseCommand(String input) throws DukeException {
        // Splits the string in an array of [command, others]
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];

        switch (command) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new PrintListCommand();
        case ("delete"):
            return new DeleteTaskCommand(inputArr[1]);
        case ("mark"):
            return new MarkTaskCommand(inputArr[1]); //Second word is the task's index
        case ("unmark"):
            return new UnmarkTaskCommand(inputArr[1]); //Second word is the task's index
        case ("todo"):
            return new ToDoCommand(inputArr);
        case ("deadline"):
            return new DeadlineCommand(inputArr);
        case ("event"):
            return new EventCommand(inputArr);
        case("find"):
            return new FindCommand(inputArr[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I do not know what does \"" + input + "\" mean. :-(");
        }
    }
}
