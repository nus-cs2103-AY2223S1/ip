package duke;

public class Parser {

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

    public Todo parseTodo(String[] args) throws DukeParseException {
        if (args.length < 2) {
            throw new DukeParseException(String.format(ExceptionMessages.EMPTY_TASK_DESCRIPTION, "todo"));
        }
        String taskDesc = args[1];
        return new Todo(taskDesc);
    }

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
     * @param args where the args[0] is "find", args[1] is the string to be matched, possibly containing spaces
     * @return the key to match task descriptions with
     * @throws DukeParseException if the input is incorrectly formatted
     */
    public String parseFind(String[] args) throws DukeParseException {
        if (args.length < 2) {
            throw new DukeParseException(ExceptionMessages.INVALID_FIND_FORMAT);
        }
        return args[1];
    }

    static class DukeParseException extends DukeException {
        public DukeParseException(String message) {
            super(message);
        }
    }
}
