package duke;
import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static java.lang.Integer.parseInt;


/**
 * Encapsulates a class extracted from the main logic to parse the user input
 */
public class Parser {

    /**
     * Parses the input and returns the appropriate command to be executed
     * @param inputString the inputted string from the user
     * @return the Command to the executed from the input
     */
    public static Command parse(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
        switch (input[0]) {
            case "bye":
                return new EndCommand();
            case "list":
                return new ListCommand();
            case "todo":
                String taskName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                return new AddCommand("todo",taskName);
            case "deadline":
                int indexOfDateDeadline = findDate(input);
                String nameDeadline = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDateDeadline));
                String stringDateDeadline = input[indexOfDateDeadline + 1];
                LocalDate dateDeadline = getDate(stringDateDeadline);
                return new AddCommand("deadline",nameDeadline,dateDeadline);
            case "event" :
                int indexOfDateEvent = findDate(input);
                String nameEvent = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDateEvent));
                String stringDateEvent = input[indexOfDateEvent + 1];
                LocalDate dateEvent = getDate(stringDateEvent);
                return new AddCommand("event",nameEvent,dateEvent);
            case "mark" :
                int indexMark = getNumber(input);
                return new MarkCommand(indexMark);
            case "unmark" :
                int indexUnmark = getNumber(input);
                return new UnmarkCommand(indexUnmark);
            case "delete" :
                int indexDelete = getNumber(input);
                return new DeleteCommand(indexDelete);
            case "date" :
                LocalDate date = getDate(input);
                return new DateCommand(date);
            case "find" :
                return new FindCommand(input[1]);
        }
        throw new DukeException("OOPS!!! I don't understand what that means");
    }

    /**
     * Returns the index from an input
     * @param input The input, split with " "
     * @return The index for the inputted command
     */
    private static int getNumber(String[] input) throws DukeException {
        if (input.length == 2 && input[1].matches("^[0-9]*$")) {
            return parseInt(input[1]);
        }
        throw new DukeException("OOPS!!! That index is not valid");
    }

    /**
     * Find the index in the parsed input where the description ends and the time begins
     * @param split The parsed input.
     * @return The index of the /by or /at in the input string. Returns -1 if not found.
     */
    private static int findDate(String[] split) throws DukeException {
        for(int i = 0; i<split.length; i++){
            if(split[i].equals("/by") || split[i].equals("/at")){
                return i;
            }
        }
        throw new DukeException("OOPS!!! Please add a date for your event with /at.");
    }

    /**
     * Returns the date from an input
     * @param input The input, split with " "
     * @return The date for the inputted command
     */
    private static LocalDate getDate(String[] input) throws DukeException {
        try {
            if (input.length == 2 && input[1].matches("^[0-9]*$")) {
                return LocalDate.parse(input[1]);
            }
            throw new DukeException("OOPS!!! I didn't understand that");
        } catch (DateTimeParseException e){
            throw new DukeException("OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }

    /**
     * Parses the input from a string to date
     * @param input The inputted string
     * @return The LocalDate returned
     */
    private static LocalDate getDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e){
            throw new DukeException("OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }






}
