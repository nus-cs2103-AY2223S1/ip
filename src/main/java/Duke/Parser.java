package Duke;

import Commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] parse = command.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        switch (parse[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                checkInputError(parse);
                return new MarkCommand(parseInt(parse[1]));
            case "unmark":
                checkInputError(parse);
                return new UnmarkCommand(parseInt(parse[1]));
            case "delete":
                checkInputError(parse);
                return new DeleteCommand(parseInt(parse[1]));
            case "todo":
                checkInputError(parse);
                return new ToDoCommand(parse[1]);
            case "deadline":
                checkInputError(parse);
                String[] parse2 = parse[1].split(" /by ");
                checkInputError(parse2);
                return new DeadlineCommand(parse2[0], LocalDate.parse(parse2[1], formatter));
            case "event":
                checkInputError(parse);
                String[] parse3 = parse[1].split(" /at ");
                checkInputError(parse3);
                return new DeadlineCommand(parse3[0], LocalDate.parse(parse3[1], formatter));
            default:
                throw new DukeException(Constants.invalid);
        }
    }

    private static void checkInputError(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(Constants.invalidInput);
        }
    }
}
