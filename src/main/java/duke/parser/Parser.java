package duke.parser;

import java.time.LocalDate;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;


/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static final char UNDONE_STATUS = ' ';
    private static final int INPUT_SPLIT_LIMIT = 2;
    private static final int TASK_TYPE_POSITION = 1;
    private static final int CHARS_BEFORE_DESCRIPTION = 7;
    private static final char TODO_SYMBOL = 'T';
    private static final char EVENT_SYMBOL = 'E';
    private static final char DEADLINE_SYMBOL = 'D';


    /**
     * Parses the user command.
     *
     * @param input The input from user.
     * @return The corresponding command class.
     * @throws DukeException Throw exception when there is an invalid user input.
     */
    public static Command parseCommand(String input) throws DukeException {
        assert !input.isEmpty() : "Input cannot be empty";
        String[] arguments = input.trim().split(" ", INPUT_SPLIT_LIMIT);
        String commandType = arguments[0];
        if ("bye".equalsIgnoreCase(commandType)) {
            return exit();
        } else if ("list".equalsIgnoreCase(commandType)) {
            return listAllTasks();
        } else if ("unmark".equalsIgnoreCase(commandType)) {
            return unMarkTask(arguments);
        } else if ("mark".equalsIgnoreCase(commandType)) {
            return markTask(arguments);
        } else if ("todo".equalsIgnoreCase(commandType)) {
            return addToDo(arguments);
        } else if ("deadline".equalsIgnoreCase(commandType)) {
            return addDeadline(arguments);
        } else if ("event".equalsIgnoreCase(commandType)) {
            return addEvent(arguments);
        } else if ("date".equalsIgnoreCase(commandType)) {
            return listTasksOnDate(arguments);
        } else if ("delete".equalsIgnoreCase(commandType)) {
            return deleteTask(arguments);
        } else if ("find".equalsIgnoreCase(commandType)) {
            return findTask(arguments);
        } else if("clone".equalsIgnoreCase(commandType)) {
            return cloneTask(arguments);
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Parse the data loaded from the file.
     * @param tasks The taskList to store the data.
     * @param line  The data loaded from the file.
     */
    public static void parseStorageData(TaskList tasks, String line) {
        char taskType = line.charAt(TASK_TYPE_POSITION);
        assert taskType == 'T' || taskType == 'D' || taskType == 'E' : "The task status should be either X or empty";
        char taskStatus = line.charAt(4);
        assert taskStatus == 'X' || taskStatus == ' ' : "The task status should be either X or empty";
        if (taskType == TODO_SYMBOL) {
            String description = line.substring(CHARS_BEFORE_DESCRIPTION);
            tasks.addTask(new ToDo(description, taskStatus));
        } else if (taskType == DEADLINE_SYMBOL) {
            parseTimeDescription(tasks, line, taskStatus, DEADLINE_SYMBOL);
        } else if (taskType == EVENT_SYMBOL) {
            parseTimeDescription(tasks, line, taskStatus, EVENT_SYMBOL);
        }
    }


    private static void parseTimeDescription(TaskList tasks, String line, char taskStatus, char taskSymbol) {
        String descriptionAndDate = line.substring(CHARS_BEFORE_DESCRIPTION);
        String[] arguments = descriptionAndDate.split("\\(");
        String description = arguments[0];
        //Remove the ")" at end of the description.
        String dateString = removeLastChar(arguments[1]);
        LocalDate date = LocalDate.parse(dateString);

        if (taskSymbol  == DEADLINE_SYMBOL) {
            tasks.addTask(new Deadline(description, date, taskStatus));
        } else if (taskSymbol  == EVENT_SYMBOL) {
            tasks.addTask(new Event(description, date, taskStatus));
        }
    }

    private static Command addToDo(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        String toDo = arguments[1];
        return new AddCommand(new ToDo(toDo, UNDONE_STATUS));
    }

    private static Command addDeadline(String[] arguments) throws EmptyDescriptionException, EmptyDateException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        String[] inputs = arguments[1].split("/");
        if (inputs.length == 1) {
            throw new EmptyDateException();
        }
        String description = inputs[0];
        LocalDate date = LocalDate.parse(extractDateByKeyword("by", inputs[1]));
        return new AddCommand(new Deadline(description, date, UNDONE_STATUS));
    }

    private static Command addEvent(String[] arguments) throws EmptyDescriptionException, EmptyDateException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }

        String[] inputs = arguments[1].split("/");
        if (inputs.length == 1) {
            throw new EmptyDateException();
        }
        String description = inputs[0];
        LocalDate date = LocalDate.parse(extractDateByKeyword("at", inputs[1]));
        return new AddCommand(new Event(description, date, UNDONE_STATUS));
    }

    private static Command deleteTask(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new DeleteCommand(taskNo);
    }

    private static Command cloneTask(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new CloneCommand(taskNo);
    }

    private static Command findTask(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        String keyword = arguments[1];
        return new FindCommand(keyword);
    }

    private static Command markTask(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new MarkCommand(taskNo);
    }

    private static Command unMarkTask(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new UnMarkCommand(taskNo);
    }

    private static Command listAllTasks() {
        return new ListCommand();
    }

    private static Command listTasksOnDate(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        LocalDate date = LocalDate.parse(arguments[1]);
        return new DateCommand(date);
    }

    private static Command exit() {
        return new ByeCommand();
    }

    private static String extractDateByKeyword(String keyword, String text) {
        assert keyword.equals("by") || keyword.equals("at") : "Wrong keyword in extractDateByKeyword function";
        String[] args = text.split(keyword);
        String date = args[1].trim();
        return date;
    }

    /**
     * Removes the last char in a string.
     *
     * @param s The string to remove last char.
     * @return The string without last char.
     */
    private static String removeLastChar(String s) {
        s = s.substring(0, s.length() - 1);
        return s;
    }
}
