package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;

import static duke.exception.ErrorMessage.INVALID_KEYWORD;
import static duke.exception.ErrorMessage.INVALID_TAG;

public class ListCommand implements Command {
    private TaskList tasks;
    private String parameter;

    public ListCommand(TaskList tasks, String parameter) {
        this.tasks = tasks;
        this.parameter = parameter;
    }
    public String execute() throws DukeException {
        if (parameter.matches("^[a-zA-Z0-9]*$")) {
            throw new DukeException(INVALID_KEYWORD, ""); //invalid keyword (contains both alphabets and digits)
        } else if (parameter.equals("#")) {
            throw new DukeException(INVALID_TAG, ""); //empty tag
        } else if (parameter.contains("#") && !parameter.substring(1).matches("[a-zA-Z]+")) {
            throw new DukeException(INVALID_TAG, ""); //invalid tag
        } else if (!parameter.matches(".*\\d.*")) {
            return String.format("list %s", parameter.trim());
        } else { //list date
            java.time.LocalDate date = Parser.parseDate(parameter);
            return String.format("list %s", date);
        }
    }
}
