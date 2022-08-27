package duke;

import duke.command.*;

import java.time.LocalDate;

public class Parser {
    private static final String DELIMIT = " ";
    private static final String ESCAPE = "/";

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
