package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.OnGoingCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidFindException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidToDoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents a parser which parses the input given by users of Duke.
 */
public class Parser {
    /**
     * Request type that can be used.
     */
    public enum RequestType {
        DONE, ONGOING, TODO, EVENT, DEADLINE, DELETE, LIST, EXIT, FIND
    }

    /**
     * Checks type of request based on the first word of input.
     * @param request the input string.
     * @return the request type.
     * @throws DukeException when the input is invalid.
     */
    public static RequestType checkRequest(String request) throws DukeException {
        if (request.equals("bye")) {
            return RequestType.EXIT;
        } else if (request.equals("list")) {
            return RequestType.LIST;
        } else if (request.matches("mark \\d+")) {
            return RequestType.DONE;
        } else if (request.matches("unmark \\d+")) {
            return RequestType.ONGOING;
        } else if (request.matches("delete \\d+")) {
            return RequestType.DELETE;
        } else if (request.matches("(?i)^(todo)(.*)")) {
            return RequestType.TODO;
        } else if (request.matches("(?i)^(find)(.*)")) {
            return RequestType.FIND;
        } else if (request.matches("(?i)^(deadline)(.*)")) {
            return RequestType.DEADLINE;
        } else if (request.matches("(?i)^(event)(.*)")) {
            return RequestType.EVENT;
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * gets the index of deleted task.
     * @param request string input belonging to delete type.
     * @param taskList array list containing all tasks.
     * @return the index.
     * @throws DukeException if the index is out of bound.
     */
    public static int getDeleteIndex(String request, TaskList taskList) throws DukeException {
        int index = Integer.parseInt(request.substring(7));
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidIndexException();
        } else {
            return index - 1;
        }
    }

    /**
     * gets index of marked task.
     * @param request input belonging to mark type.
     * @param taskList array list containing all tasks.
     * @return the index.
     * @throws DukeException if the index is out of bounds.
     */
    public static int getMarkIndex(String request, TaskList taskList) throws DukeException {
        int index = Integer.parseInt(request.substring(5));
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidIndexException();
        } else {
            return index - 1;
        }
    }

    /**
     * gets index of unmarked task.
     * @param request input belonging to ongoing type.
     * @param taskList array list containing all tasks.
     * @return the index.
     * @throws DukeException if the index is out of bounds.
     */
    public static int getUnMarkIndex(String request, TaskList taskList) throws DukeException {
        int index = Integer.parseInt(request.substring(7));
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidIndexException();
        } else {
            return index - 1;
        }
    }

    /**
     * Retrieves todo task description from input.
     * @param request input contain description.
     * @return String description.
     * @throws DukeException If the description is empty.
     */
    public static String todoTask(String request) throws DukeException {
        String[] rq = request.split(" ", 2);
        if (rq.length < 2 || rq[1].trim().equals("")) {
            throw new InvalidToDoException();
        } else {
            return rq[1];
        }
    }

    /**
     * Retrieves keyword from input of find type.
     * @param request input belonging to find type.
     * @return keyword string.
     * @throws DukeException if the keyword is empty.
     */
    public static String findTask(String request) throws DukeException {
        String[] rq = request.split(" ", 2);
        if (rq.length < 2 || rq[1].trim().equals("")) {
            throw new InvalidFindException();
        } else {
            return rq[1];
        }
    }

    /**
     * Retrieves keyword from input of deadline type.
     * @param request input belonging to deadline type.
     * @return String[] containing description and date.
     * @throws DukeException if description or date is missing or date is invalid.
     */
    public static String[] deadlineTask(String request) throws DukeException {
        if (request.matches("(?i)^deadline\\s.+\\s\\/(by)\\s.+")) {
            String[] deadline = request.substring(9).split("\\/(by)\\s", 2);
            if (DateConverter.isValidDate(deadline[1])) {
                return deadline;
            } else {
                throw new InvalidDeadlineException("Hmm, You need to use yyyy-mm-dd for date format.");
            }
        } else {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Splits event command into description and duration.
     * @param request input belonging to the event type.
     * @return String[] containing the description at index 0 and duration at index 1.
     * @throws DukeException if no description or duration that are separated by "/at".
     */
    public static String[] eventTask(String request) throws DukeException {
        if (request.matches("(?i)^event\\s.+\\s\\/(at)\\s.+")) {
            return request.substring(6).split("\\/(at)\\s", 2);
        } else {
            throw new InvalidEventException();
        }
    }
    /**
     * Parses the input string and returns a command for Duke to execute.
     * @param userInput input needed to be parsed.
     * @param taskList TaskList object from Duke class.
     * @return Command to execute.
     * @throws DukeException if input is invalid.
     */
    public static Command parse(String userInput, TaskList taskList) throws DukeException {
        RequestType rqType = checkRequest(userInput);
        switch (rqType) {
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(getMarkIndex(userInput, taskList));
        case ONGOING:
            return new OnGoingCommand(getUnMarkIndex(userInput, taskList));
        case DELETE:
            return new DeleteCommand(getDeleteIndex(userInput, taskList));
        case FIND:
            return new FindCommand(findTask(userInput));
        case TODO:
            return new AddCommand(new ToDo(todoTask(userInput)));
        case DEADLINE:
            String[] deadline = deadlineTask(userInput);
            return new AddCommand(new Deadline(deadline[0], deadline[1]));
        case EVENT:
            String[] event = eventTask(userInput);
            return new AddCommand(new Event(event[0], event[1]));
        default:
            return new ExitCommand();
        }
    }
}
