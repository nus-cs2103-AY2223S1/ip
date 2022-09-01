package duke;

import duke.messages.ExceptionMessages;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Processes the entered command.
 * Checks for formatting errors.
 */
public class Parser {

    /**
     * Gets the index for commands of type (command) (index)
     * @param args String[] of format [commandName, restOfCommand]
     * @return the desired index
     * @throws DukeParseException if length of input array is wrong or a number is not provided
     */
    public int parseIndex(String[] args) throws DukeParseException {
        if (args.length !=  2) {
            throw new DukeParseException(String.format(ExceptionMessages.INVALID_INDEX_FORMAT, "remove"));
        }
        try {
            return Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeParseException(String.format(ExceptionMessages.INVALID_INDEX_FORMAT, "remove"));
        }
    }

    /**
     * Handles the creation of a "Todo" task from user input
     * @param args String[] of format ["todo", restOfInput]
     * @return the created Todo
     * @throws DukeParseException if the format is incorrect
     */
    public Todo parseTodo(String[] args) throws DukeParseException {
        if (args.length < 2) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "todo"));
        }
        String taskDesc = args[1];
        return new Todo(taskDesc);
    }

    /**
     * Handles the creation of a "Deadline" task from user input
     * @param args String[] of format ["deadline", restOfInput]
     * @return the created Deadline
     * @throws DukeParseException if the format is incorrect
     */
    public Deadline parseDeadline(String[] args) throws DukeParseException {
        if (args.length < 2) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "deadline"));
        }
        String temp = args[1];
        String[] tempStrArr = temp.split("\\s+/by\\s+", 2);
        if (tempStrArr.length < 2) {;
            throw new DukeParseException(String.format(ExceptionMessages.INVALID_FORMAT, "/by"));
        }
        String taskDesc = tempStrArr[0];
        if (taskDesc.equals("")) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "deadline"));
        }
        String taskTime = tempStrArr[1];
        if (taskTime.equals("")) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_TIME, "deadline"));
        }
       return new Deadline(taskDesc, taskTime);
    }

    /**
     * Handles the creation of a "Event" task from user input
     * @param args String[] of format ["event", restOfInput]
     * @return the created Event
     * @throws DukeParseException if the format is incorrect
     */
    public Event parseEvent(String[] args) throws DukeParseException {
        if (args.length < 2) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "event"));
        }
        String temp =  args[1];
        String[] tempStrArr = temp.split("\\s+/at\\s+", 2);
        if (tempStrArr.length < 2) {
            throw new DukeParseException(String.format(ExceptionMessages.INVALID_FORMAT, "/at"));
        }
        String taskDesc = tempStrArr[0];
        if (taskDesc.equals("")) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "event"));
        }
        String taskTime = tempStrArr[1];
        if (taskTime.equals("")) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_TIME, "event"));
        }
        return new Event(taskDesc, taskTime);
    }

    /**
     * Validates find commmand input
     * @param args String[] of format ["find", keyToMatch]
     * @return the key to match task descriptions with
     * @throws DukeParseException if the format is incorrect
     */
    public String parseFind(String[] args) throws DukeParseException {
        if (args.length < 2) {
            throw new DukeParseException(ExceptionMessages.INVALID_FIND_FORMAT);
        }
        return args[1];
    }

    /**
     * Represents exceptions specific to this parser
     */
    static class DukeParseException extends DukeException {
        public DukeParseException(String message) {
            super(message);
        }
    }
}
