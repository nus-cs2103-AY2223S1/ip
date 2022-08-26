package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private final String exit = "bye";

    public Parser() {

    }

    public Command parse(TaskList taskList, String input, Ui ui) throws DukeException {
        input = input.toLowerCase();
        if(input.equals(exit)) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else {
            String[] substr = input.split(" ", 2); // to identify the keyword used
            Integer index;
            Task temp;
            switch (substr[0]) {
                case "mark":
                    if(substr.length == 1) { // no number was given
                        throw new DukeException(DukeException.MISSING_INDEX);
                    }
                    try {
                        index = Integer.parseInt(substr[1]) - 1;
                        if(index < 0 || index >= taskList.getSize()) { // to check if index is out of range
                            throw new DukeException(DukeException.OUT_OF_RANGE);
                            //System.out.println("thrs nth there :<");
                        }

                        return new MarkCommand(index);
                    } catch (NumberFormatException e) {
                        throw new DukeException(DukeException.WRONG_FORMAT);
                        //ui.showError("Invalid input"); // if index given cannot be converted or was the wrong format
                    }
                case "unmark":
                    if(substr.length == 1) {
                        throw new DukeException(DukeException.MISSING_INDEX);
                    }
                    try {
                        index = Integer.parseInt(substr[1]) - 1;
                        if(index < 0 || index >= taskList.getSize()) { // check if index is out of range
                            throw new DukeException(DukeException.OUT_OF_RANGE);
                        }
                        return new UnmarkCommand(index);

                    } catch (NumberFormatException e) {
                        throw new DukeException(DukeException.WRONG_FORMAT);
                        // ui.showError("Invalid input");
                    }

                case "delete":
                    if(substr.length == 1) {
                        throw new DukeException(DukeException.MISSING_INDEX);
                    }
                    try {
                        index = Integer.parseInt(substr[1]) - 1;
                        if(index < 0 || index >= taskList.getSize()) {
                            throw new DukeException(DukeException.OUT_OF_RANGE);

                        }
                        return new DeleteCommand(index);
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid input");
                    }
                    break;
                case "todo":
                    if(substr.length == 1) {
                        throw new DukeException(DukeException.MISSING_DESCRIPTION);
                    }
                    temp = new Todo(substr[1]);
                    return new AddCommand(temp);

                case "deadline":
                    if(substr.length == 1) {
                        throw new DukeException(DukeException.MISSING_DESCRIPTION);
                    }
                    String[] dlDesc = substr[1].split(" /by ", 2);
                    if(dlDesc.length < 2) {
                        throw new DukeException(DukeException.MISSING_DATE);
                    }
                    try {
                        LocalDate date = LocalDate.parse(dlDesc[1]);
                        temp = new Deadline(dlDesc[0], date);
                        return new AddCommand(temp);
                        //break;
                    } catch (DateTimeParseException e) {
                        throw new DukeException(DukeException.WRONG_FORMAT_DATE);
                        //ui.showError("Please re-enter the task with the following deadline format: \nyyyy-mm-dd");

                    }


                case "event":
                    if(substr.length == 1) {
                        throw new DukeException(DukeException.MISSING_DESCRIPTION);
                    }
                    String[] eventDesc = substr[1].split(" /at ", 2);
                    if(eventDesc.length < 2) {
                        throw new DukeException(DukeException.MISSING_DATE);
                    }
                    try {
                        String[] timeDesc = eventDesc[1].split(" ", 2);
                        if(timeDesc.length > 1) {
                            LocalDate date = LocalDate.parse(timeDesc[0]);
                            temp = new Event(eventDesc[0], date, timeDesc[1]);
                        } else {
                            LocalDate date = LocalDate.parse(eventDesc[1]);
                            temp = new Event(eventDesc[0], date);
                        }

                        return new AddCommand(temp);
                    } catch (DateTimeParseException e) {
                        throw new DukeException(DukeException.WRONG_FORMAT_DATE);
                        //ui.showError("Please re-enter the task with the following deadline format: \nyyyy-mm-dd");

                    }
                default:
                    throw new DukeException(DukeException.UNRECOGNISED_COMMAND);
            }

        }
        return new ExitCommand();
    }
}