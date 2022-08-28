package Duke;

import Commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

/**
 * Represents the parser for Duke
 */
public class Parser {

    /**
     * Returns command that would be executed
     *
     * @param command Command entered by user
     * @return Command
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException {
        String[] parse = command.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        switch (parse[0]) {
        case "bye":
            checkInputLength(parse, 1);
            assert parse.length == 1: "Error in input for bye";
            return new ExitCommand();
        case "list":
            checkInputLength(parse, 1);
            assert parse.length == 1: "Error in input for list";
            return new ListCommand();
        case "mark":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for mark";
            return new MarkCommand(parseInt(parse[1]));
        case "unmark":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for unmark";
            return new UnmarkCommand(parseInt(parse[1]));
        case "delete":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for delete";
            return new DeleteCommand(parseInt(parse[1]));
        case "find":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for find";
            return new FindCommand(parse[1]);
        case "priority":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for priority";
            String[] parse0 = parse[1].split(" ");
            checkInputLength(parse0,2);
            return new PriorityCommand(parseInt(parse0[0]), parse0[1]);
        case "todo":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for todo";
            return new ToDoCommand(parse[1]);
        case "deadline":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for deadline";
            String[] parse2 = parse[1].split(" /by ");
            checkInputLength(parse2,2);
            return new DeadlineCommand(parse2[0], LocalDate.parse(parse2[1], formatter));
        case "event":
            checkInputLength(parse,2);
            assert parse.length == 2: "Error in input for event";
            String[] parse3 = parse[1].split(" /at ");
            checkInputLength(parse3,2);
            return new EventCommand(parse3[0], LocalDate.parse(parse3[1], formatter));
        default:
            throw new DukeException(Constants.invalid);
        }
    }

    /**
     * Checks if length of array is as expected
     *
     * @param arr
     * @throws DukeException
     */
    private static void checkInputLength(String[] arr, int length) throws DukeException {
        if (arr.length != length) {
            throw new DukeException(Constants.invalidInput);
        }
    }
}
