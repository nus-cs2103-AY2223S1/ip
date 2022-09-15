package bobthebot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobthebot.command.DeadlineCommand;
import bobthebot.command.DeleteCommand;
import bobthebot.command.EventCommand;
import bobthebot.command.FindCommand;
import bobthebot.command.ListCommand;
import bobthebot.command.MarkCommand;
import bobthebot.command.TodoCommand;
import bobthebot.command.UnmarkCommand;
import bobthebot.exceptions.BobException;
import bobthebot.exceptions.InvalidDateTimeException;
import bobthebot.tasks.ToDoList;


/**
 * Parser class which handles the logic of how the handle the input.
 */
public class Parser {
    /**
     * Takes in the user's command and handles the logic of the output.
     *
     * @param command A String containing the user's input.
     * @param list The Todo List which the command will act on.
     * @return String representing result of the command.
     * @throws BobException If command is invalid.
     */
    public String parseCommand(String command, ToDoList list) throws BobException {
        String[] splitCommand = command.trim().split("\\s+", 2);
        switch (splitCommand[0]) {
        case "list":
            return parseList(list);
        case "mark":
            return parseMark(splitCommand, list);
        case "unmark":
            return parseUnmark(splitCommand, list);
        case "delete":
            return parseDelete(splitCommand, list);
        case "find":
            return parseFind(splitCommand, list);
        case "todo":
            return parseTodo(splitCommand, list);
        case "deadline":
            return parseDeadline(splitCommand, list);
        case "event":
            return parseEvent(splitCommand, list);
        default:
            throw new BobException(LanguageBank.INVALID_INPUT_ERROR_MESSAGE);
        }
    }

    /**
     * Parses the "list" command, which shows the user the items in the ToDo list, their type and status.
     *
     * @param list ToDoList which "list" command acts on.
     * @return String representing the output of the "list" command.
     */
    private String parseList(ToDoList list) {
        ListCommand listCommand = new ListCommand(list);
        return listCommand.execute();
    }

    /**
     * Parses the "mark" command, which marks an item on the list as done.
     *
     * @param splitCommand A String array containing the "mark" command and it's argument.
     * @param list ToDoList which "mark" command acts on.
     * @return String representing the output of the "mark" command.
     * @throws BobException If command is invalid (eg. list is empty, no argument given, invalid argument).
     */
    private String parseMark(String[] splitCommand, ToDoList list) throws BobException {
        if (list.getLength() == 0) {
            throw new BobException(LanguageBank.MARK_DONE_EMPTY_LIST_ERROR_MESSAGE);
        }

        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.MARK_DONE_INVALID_INDEX_ERROR_MESSAGE);
        }

        int index = Integer.parseInt(splitCommand[1]);

        if (index <= 0) {
            throw new BobException(LanguageBank.MARK_DONE_INVALID_INDEX_ERROR_MESSAGE);
        }

        MarkCommand markCommand = new MarkCommand(index, list);
        return markCommand.execute();
    }

    /**
     * Parses the "unmark" command, which marks an item on the list as undone.
     *
     * @param splitCommand A String array containing the "unmark" command and its argument.
     * @param list ToDoList which "unmark" command acts on.
     * @return String representing the output of the "unmark" command.
     * @throws BobException If command is invalid (eg. list is empty, no argument given, invalid argument).
     */
    private String parseUnmark(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.MARK_UNDONE_INVALID_INDEX_ERROR_MESSAGE);
        }

        if (list.getLength() == 0) {
            throw new BobException(LanguageBank.MARK_UNDONE_EMPTY_LIST_ERROR_MESSAGE);
        }

        int index = Integer.parseInt(splitCommand[1]);

        if (index <= 0) {
            throw new BobException(LanguageBank.MARK_UNDONE_INVALID_INDEX_ERROR_MESSAGE);
        }

        UnmarkCommand unmarkCommand = new UnmarkCommand(index, list);
        return unmarkCommand.execute();
    }

    /**
     * Parses the "delete" command, which deletes the specified element in the list.
     *
     * @param splitCommand A String array containing the "delete" command and its argument.
     * @param list ToDoList which "mark" command acts on.
     * @return String representing the output of the "mark" command.
     * @throws BobException If command is invalid (eg. list is empty, no argument given, invalid argument).
     */
    private String parseDelete(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.DELETE_INVALID_INDEX_ERROR_MESSAGE);
        }

        int index = Integer.parseInt(splitCommand[1]);
        if (index > list.getLength() || index <= 0) {
            throw new BobException(LanguageBank.DELETE_EMPTY_LIST_ERROR_MESSAGE);
        }

        DeleteCommand deleteCommand = new DeleteCommand(index, list);
        return deleteCommand.execute();
    }

    /**
     * Parses the "find" command, which finds the elements in the ToDo list which match with
     *     the argument of the command.
     *
     * @param splitCommand A String array containing the "find" command and its argument.
     * @param list ToDoList which "find" command acts on.
     * @return String representing the output of the "find" command.
     * @throws BobException If command is invalid (eg. list is empty, no argument given, invalid argument).
     */
    private String parseFind(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.FIND_INVALID_FORMAT_ERROR_MESSAGE);
        }
        FindCommand findCommand = new FindCommand(list, splitCommand[1]);
        return findCommand.execute();
    }

    /**
     * Parses the "todo" command, which if valid, will add the specified todo task to the ToDoList.
     *
     * @param splitCommand A String array containing the "todo" command and its argument.
     * @param list ToDoList which "todo" command acts on.
     * @return String representing the output of the "todo" command.
     * @throws BobException If command is invalid (eg. no argument given).
     */
    private String parseTodo(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.TODO_INVALID_FORMAT_ERROR_MESSAGE);
        }

        TodoCommand todoCommand = new TodoCommand(splitCommand[1], list);
        return todoCommand.execute();
    }

    /**
     * Parses the "deadline" command, which if valid, will add the specified deadline task to the ToDoList.
     *
     * @param splitCommand A String array containing the "deadline" command and its argument.
     * @param list ToDoList which "deadline" command acts on.
     * @return String representing the output of the "deadline" command.
     * @throws BobException If command is invalid (eg. no argument given, wrong date-time format).
     */
    private String parseDeadline(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.DEADLINE_INVALID_FORMAT_ERROR_MESSAGE);
        }

        String[] splitDeadline = splitCommand[1].split(" /by ");

        if (splitDeadline.length < 2) {
            throw new BobException(LanguageBank.DEADLINE_INVALID_FORMAT_ERROR_MESSAGE);
        }

        String dueDate = splitDeadline[1];

        try {
            parseDateTime(dueDate);
        } catch (InvalidDateTimeException exception) {
            throw new BobException(exception.getMessage());
        }

        DeadlineCommand deadlineCommand = new DeadlineCommand(splitDeadline[0], splitDeadline[1], list);
        return deadlineCommand.execute();
    }

    /**
     * Parses the "event" command, which if valid, will add the specified deadline task to the ToDoList.
     *
     * @param splitCommand A String array containing the "event" command and its argument.
     * @param list ToDoList which "event" command acts on.
     * @return String representing the output of the "event" command.
     * @throws BobException If command is invalid (eg. no argument given, wrong date-time format).
     */
    private String parseEvent(String[] splitCommand, ToDoList list) throws BobException {
        if (splitCommand.length < 2) {
            throw new BobException(LanguageBank.EVENT_INVALID_FORMAT_ERROR_MESSAGE);
        }

        String[] splitEvent = splitCommand[1].split(" /at ");

        if (splitEvent.length < 2) {
            throw new BobException(LanguageBank.EVENT_INVALID_FORMAT_ERROR_MESSAGE);
        }

        String eventDate = splitEvent[1];

        try {
            parseDateTime(eventDate);
        } catch (InvalidDateTimeException exception) {
            throw new BobException(exception.getMessage());
        }

        EventCommand eventCommand = new EventCommand(splitEvent[0], splitEvent[1], list);
        return eventCommand.execute();
    }

    /**
     * Parses a given String representing a given dateTime.
     *
     * @param dateTime A String containing a valid or invalid dateTime.
     * @throws InvalidDateTimeException If the given string does not contain a valid dateTime.
     */
    private void parseDateTime(String dateTime) throws InvalidDateTimeException {
        LocalDateTime currDate = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = null;
        LocalDateTime deadlineDate = null;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm");
            deadlineDate = LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateTimeException(LanguageBank.DATE_INVALID_FORMAT_ERROR_MESSAGE);
        }

        Boolean isAfterCurrentDate = deadlineDate.isAfter(currDate);

        dateTime = dateTime.trim();
        // Solution below adapted from
        // https://stackoverflow.com/questions/37732/what-is-the-regex-pattern-for-datetime-2008-09-01-123545
        String regex = "(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2})(\\d{2})";

        if (!dateTime.matches(regex)) {
            throw new InvalidDateTimeException(LanguageBank.DATE_INVALID_FORMAT_ERROR_MESSAGE);
        }

        if (dateTime.matches(regex) && !isAfterCurrentDate) {
            throw new InvalidDateTimeException(LanguageBank.DATE_BEFORE_PRESENT_ERROR_MESSAGE);
        }
    }
}
