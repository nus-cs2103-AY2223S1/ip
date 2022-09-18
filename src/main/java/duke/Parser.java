package duke;

import duke.command.*;
import duke.exceptions.DukeDateFormatException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeMissingParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * A parser class which parses the user input to make it understandable to the duke program.
 */
public class Parser {
    private static final String DELIMIT = " ";
    private static final String ESCAPE = "/";

    /**
     * Parses the user input and returns a command based on the input.
     *
     * @param input User input.
     * @return Command based on the user input.
     * @throws DukeException Errors in the user input.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);
        String head = inputs[0].toUpperCase();
        Command res;
        switch (head) {
        case "BYE":
            res = new ExitCommand();
            break;
        case "LIST":
            res = new ListCommand();
            break;
        case "MARK":
            res = new MarkCommand(inputs);
            break;
        case "UNMARK":
            res = new UnmarkCommand(inputs);
            break;
        case "DELETE":
            res = new DeleteCommand(inputs);
            break;
        case "FIND":
            res = new FindCommand(inputs);
            break;
        case "TAG":
            res = new TagCommand(inputs);
            break;
        default:
            res = new AddCommand(inputs);
        }
        return res;
    }

    /**
     * Parses the user input and returns the description of an add task user input.
     *
     * @param inputs User input parsed by parse method.
     * @param escape Escape character i.e. "/by" and "/at".
     * @return String containing the description of the task.
     */
    public static String getDescription(String[] inputs, String escape) {
        String description = null;
        
        try {
            description = inputs[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            if (escape == null) {
                throw new DukeMissingParameterException("todo <description>", "description");
            } else if (escape.equals("/by")) {
                throw new DukeMissingParameterException("deadline <description> /by <date>", "description");
            } else if (escape.equals("/at")) {
                throw new DukeMissingParameterException("event <description> /at <date>", "description");
            }
        }

        if (escape == null) {
            return description;
        }
        return description.split(" " + escape + " ")[0];
    }

    /**
     * Parses the user input and returns the LocalDate of an add task user input.
     *
     * @param inputs User input parsed by parse method.
     * @param escape Escape character i.e. "/by" and "/at".
     * @return a LocalDate.
     */
    public static LocalDate getDate(String[] inputs, String escape) {
        String date = null;
        LocalDate res = null;

        try {
            String[] parameters = inputs[1].split(" " + escape + " ");
            date = parameters[1];
            res = LocalDate.parse(date);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (escape.equals("/at")) {
                throw new DukeMissingParameterException("event <description> /at <date>", "<date>");
            }
            if (escape.equals("/by")) {
                throw new DukeMissingParameterException("deadline <description> /by <date>", "<date>");
            }
        } catch (DateTimeParseException e) {
            throw new DukeDateFormatException(date);
        }

        assert res != null;
        return res;
    }

}
