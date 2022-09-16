package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static duke.DukeConstants.EXIT;

/**
 * This class handles the parsing of user commands.
 */
public class Parser {

    /**
     * Constructor for the Parser class.
     */
    public Parser() {

    }

    /**
     * Parses the user input and returns a Command
     * for the program to respond to.
     *
     * @param taskList TaskList to be used.
     * @param input User input.
     * @param ui Ui for display.
     * @return Command for program to execute.
     * @throws DukeException Exception to be thrown
     */
    public Command parse(TaskList taskList, String input, Ui ui) throws DukeException {
        input = input.toLowerCase();
        if (input.equals(EXIT)) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("poyo")) { // for fun
            return new NullCommand("poyo");
        } else {
            String[] subStrs = input.split(" ", 2); // to identify the keyword used
            assert subStrs.length < 3 : "There should be 2 or less strings";
            try {
                return parseTaskCommands(taskList, subStrs);
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    private Command parseTaskCommands(TaskList taskList, String[] subStrs) throws DukeException {
        try {
            switch (subStrs[0]) {
            case "mark":
                // fallthrough
            case "unmark":
                return parseMarking(taskList, subStrs);
            case "delete":
                return parseDelete(taskList, subStrs);
            case "todo":
                return parseAddTodo(subStrs);
            case "deadline":
                return parseAddDeadline(subStrs);
            case "event":
                return parseAddEvent(subStrs);
            case "find":
                return parseFind(subStrs);
            case "show":
                return parseShow(taskList, subStrs);
            case "archive":
                return parseArchive(taskList, subStrs);
            default:
                throw new DukeException(DukeException.UNRECOGNISED_COMMAND);
            }
        } catch (DukeException e) {
            throw e;
        }

    }

    private Command parseShow(TaskList taskList, String[] subStrs) throws DukeException {
        int index;
        if (subStrs.length == 1) {
            throw new DukeException(DukeException.MISSING_INDEX);
        }
        try {
            index = Integer.parseInt(subStrs[1]) - 1;
            if (index < 0 || index >= taskList.getSize()) { // check if index is out of range
                throw new DukeException(DukeException.OUT_OF_RANGE);
            }
            return new ShowCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.WRONG_FORMAT);
        }
    }
    private Command parseMarking(TaskList taskList, String[] subStrs) throws DukeException {
        int index;
        if (subStrs.length == 1) {
            throw new DukeException(DukeException.MISSING_INDEX);
        }
        try {
            index = Integer.parseInt(subStrs[1]) - 1;
            if (index < 0 || index >= taskList.getSize()) { // check if index is out of range
                throw new DukeException(DukeException.OUT_OF_RANGE);
            }
            if(subStrs[0].equals("unmark")) {
                return new UnmarkCommand(index);
            } else {
                return new MarkCommand(index);
            }

        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.WRONG_FORMAT);
        }
    }

    private Command parseDelete(TaskList taskList, String[] subStrs) throws DukeException {
        if (subStrs.length == 1) {
            throw new DukeException(DukeException.MISSING_INDEX);
        }
        try {
            int index = Integer.parseInt(subStrs[1]) - 1;
            if (index < 0 || index >= taskList.getSize()) {
                throw new DukeException(DukeException.OUT_OF_RANGE);
            }
            assert taskList.getSize() > 0 : "Tasklist should contain items";
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new NullCommand("invalid input");
        }
    }

    private Command parseAddTodo(String[] subStrs) throws DukeException {
        if (subStrs.length == 1) {
            throw new DukeException(DukeException.MISSING_DESCRIPTION);
        }
        Task temp = new Todo(subStrs[1]);
        return new AddCommand(temp);
    }

    private Command parseAddDeadline(String[] subStrs) throws DukeException {
        if (subStrs.length == 1) {
            throw new DukeException(DukeException.MISSING_DESCRIPTION);
        }
        String[] dlDescs = subStrs[1].split(" /by ", 2);
        if (dlDescs.length < 2) {
            throw new DukeException(DukeException.MISSING_DATE);
        }
        try {
            LocalDate date = LocalDate.parse(dlDescs[1]);
            Task temp = new Deadline(dlDescs[0], date);
            return new AddCommand(temp);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.WRONG_FORMAT_DATE);
        }
    }

    private Command parseAddEvent(String[] subStrs) throws DukeException {
        if (subStrs.length == 1) {
            throw new DukeException(DukeException.MISSING_DESCRIPTION);
        }
        String[] eventDescs = subStrs[1].split(" /at ", 2);
        if (eventDescs.length < 2) {
            throw new DukeException(DukeException.MISSING_DATE);
        }
        try {
            Task temp;
            String[] timeDescs = eventDescs[1].split(" ", 2);
            if (timeDescs.length > 1) {
                LocalDate date = LocalDate.parse(timeDescs[0]);
                temp = new Event(eventDescs[0], date, timeDescs[1]);
            } else {
                LocalDate date = LocalDate.parse(eventDescs[1]);
                temp = new Event(eventDescs[0], date);
            }
            return new AddCommand(temp);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.WRONG_FORMAT_DATE);
        }
    }

    private Command parseFind(String[] subStrs) throws DukeException {
        if (subStrs.length == 1) { // no number was given
            throw new DukeException(DukeException.MISSING_DESCRIPTION);
        } else {
            return new FindCommand(subStrs[1]);
        }
    }

    private Command parseArchive(TaskList taskList, String[] subStrs) throws DukeException {
        if (subStrs.length == 1) { // no number was given
            throw new DukeException(DukeException.MISSING_DESCRIPTION);
        }
        if (subStrs.length > 2) {
            throw new DukeException(DukeException.UNRECOGNISED_COMMAND);
        }
        String keyword = subStrs[1];
        if (keyword.equals("load")) {
            return new ArchiveCommand(ArchiveCommand.ArchiveType.LOAD);
        }
        if (keyword.equals("all")) {
            return new ArchiveCommand(ArchiveCommand.ArchiveType.ARCHIVE);
        }
        try {
            int index = Integer.parseInt(subStrs[1]) - 1;
            if (index < 0 || index >= taskList.getSize()) {
                throw new DukeException(DukeException.OUT_OF_RANGE);
            }
            assert taskList.getSize() > 0 : "Tasklist should contain items";
            return new ArchiveCommand(ArchiveCommand.ArchiveType.INDEX, index);
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.UNRECOGNISED_COMMAND);
        }
    }
}