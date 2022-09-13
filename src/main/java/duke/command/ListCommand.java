package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;

import static duke.exception.ErrorMessage.INVALID_KEYWORD;
import static duke.exception.ErrorMessage.INVALID_TAG;

/**
 * Represents a ListCommand to list tasks by date/ description keyword/ tag. This class implements Command Interface.
 * The command to list all tasks need not modify/ search TaskList, therefore it is not handled by this class.
 */
public class ListCommand implements Command {
    private TaskList tasks;
    private String parameter;

    /**
     * This method is a ListCommand constructor.
     * A ListCommand consists of a TaskList and a parameter string containing date/ keyword/ tag.
     * @param tasks a list of tasks.
     * @param parameter a string containing date/ keyword/ tag.
     */
    public ListCommand(TaskList tasks, String parameter) {
        this.tasks = tasks;
        this.parameter = parameter;
    }

    /**
     * Executes list command to list tasks in the TaskList by date/ description keyword/ tag.
     * Returns a string containing commandType and task index (1-indexed).
     * Returned value will be used to call chatbot response message.
     * If tag/ keyword is invalid, the command is not executed.
     *
     * @throws DukeException if tag/ keyword is invalid.
     * @return a string containing commandType and task index.
     */
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
