package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Date;
import duke.DukeException;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkTaskCommand;
import duke.command.PrintListCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkTaskCommand;


/**
 * Parses tbe input received by the AIlfred bot.
 * @author Jason
 */
public class Parser {

    /**
     * Handles the parsing of dates to create Deadlines or Events.
     * @param input String input of date.
     * @return Date object used to construct Deadlines or Events.
     * @throws DukeException Exception if date format is wrong.
     */
    public static Date parseDate(String input) throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsed = LocalDate.parse(input, dtf);

            return new Date(parsed);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please follow the Date and Time Format: yyyy-MM-dd [2000-01-01]");
        }

    }

    /**
     * Handles the parsing of dates from save file data for Deadlines or Events.
     * @param input String input of date.
     * @return Date object used to construct Deadlines or Events.
     * @throws DukeException Exception if date format is wrong.
     */
    public static Date parseDateSave(String input) throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate parsed = LocalDate.parse(input, dtf);
            return new Date(parsed);

        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The save file is corrupted.");
        }

    }

    /**
     * Handles the parsing of inputs given by the user to AIlfred.
     * @param input String input of the entire command.
     * @return Command object dictating how the command should be run.
     * @throws DukeException Exception if input is not one of the listed commands.
     */
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
