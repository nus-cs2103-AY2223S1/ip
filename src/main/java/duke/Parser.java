package duke;

import duke.command.*;
import java.time.LocalDate;

/**
 * A parser class which parses the user input to make it understandable to the duke program.
 */
public class Parser {
    private static final String DELIMIT = " ";
    private static final String ESCAPE = "/";

    /**
     * A static method which parses the user input and returns a command based on the input.
     *
     * @param input user input
     * @return command based on the user input
     * @throws DukeException errors in the user input
     */
    public static Command parse(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);
        String head = inputs[0].toUpperCase();
        Command res;
        try {
            switch (head) {
                case "BYE":
                    res = new ExitCommand();
                    break;
                case "LIST":
                    res = new ListCommand();
                    break;
                case "MARK":
                    res = new MarkCommand(Integer.parseInt(inputs[1]) - 1);
                    break;
                case "UNMARK":
                    res = new UnmarkCommand(Integer.parseInt(inputs[1]) - 1);
                    break;
                case "DELETE":
                    res = new DeleteCommand(Integer.parseInt(inputs[1]) - 1);
                    break;
                default:
                    res = new AddCommand(inputs);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing parameters (require Integer) for the command!");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid parameters (require Integer) for the command!");
        }
        return res;
    }

    /**
     * Parses the user input and returns the description of an add task user input.
     *
     * @param inputs user input parsed by parse method
     * @param escape an escape character i.e. "/by" and "/at"
     * @return a string containing the description of the task
     */
    public static String getDescription(String[] inputs, String escape) {
        String description;
        try {
            description = inputs[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Descriptions cannot be empty!");
        }

        if (escape == null) {
            return description;
        }

        return description.split(" " + escape + " ")[0];
    }

    /**
     * Parses the user input and returns the LocalDate of an add task user input.
     *
     * @param inputs user input parsed by parse method
     * @param escape an escape character i.e. "/by" and "/at"
     * @return a LocalDate
     */
    public static LocalDate getTime(String[] inputs, String escape) {
        String date;
        try {
            String[] parameters = inputs[1].split(" " + escape + " ");
            date = parameters[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Dates cannot be empty!");
        }
        return LocalDate.parse(date);
    }

}
