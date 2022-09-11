package rabbit.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import rabbit.task.Task;
import rabbit.task.Event;
import rabbit.task.Deadline;
import rabbit.task.Todo;

import rabbit.exception.AddToListException;
import rabbit.exception.MarkUnmarkException;
import rabbit.exception.DeleteException;
import rabbit.exception.ImportDataException;
import rabbit.exception.FindFormatException;

public class Parser {
    public Parser(){};

    /**
     * Parses the lines in data.txt to tasks that
     * are added into the list.
     *
     * @param input lines of tasks from data.txt.
     * @return the task to be added.
     * @throws ImportDataException
     */
    public static Task parseImport(String input) throws ImportDataException {
        String type;
        boolean isDone;
        String content;
        LocalDateTime time;

        try {
            String[] splitContent = input.split("\\|", 4);
            type = splitContent[0];

            if (type.equals("E") || type.equals("D")) {
                isDone = splitContent[1].equals("X");
                content = splitContent[2];
                time = parseTime(splitContent[3]);
                if (type.equals("E")) {
                    return new Event(content, time, isDone);
                } else {
                    return new Deadline(content, time, isDone);
                }
            } else if (type.equals("T")) {
                isDone = splitContent[1].equals("X");
                content = splitContent[2];
                return new Todo(content, isDone);
            } else {
                throw new ImportDataException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new ImportDataException();
        } catch (AddToListException e) {
            throw new ImportDataException();
        } catch (NullPointerException e) {
            throw new ImportDataException();
        }
    }


    /**
     * Returns localDataTime according to the input.
     *
     * @param input the time input from the user.
     * @return a LocalDataTime instance.
     */
    public static LocalDateTime parseTime(String input) throws AddToListException {
        try {
            String[] splitTime = input.split("-", 4);
            int year = Integer.parseInt(splitTime[0]);
            int month = Integer.parseInt(splitTime[1]);
            int day = Integer.parseInt(splitTime[2]);
            int hour = Integer.parseInt(splitTime[3].substring(0, 2));
            int minute = Integer.parseInt(splitTime[3].substring(2, 4));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException e) {
            throw new AddToListException(AddToListException.Type.FORMAT);
        } catch (DateTimeException e) {
            throw new AddToListException(AddToListException.Type.TIMEFORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            throw new AddToListException(AddToListException.Type.TIMEFORMAT);
        }
    }

    /**
     * Returns the content and time of a task.
     *
     * @param input the string after the task type in the input.
     * @return an array of strings whose first element is the
     * content and second element is the time.
     */
    public static String[] parseContent(String input) {
        String[] splitContent;
        splitContent = input.split("\\s+" + "/");
        return splitContent;
    }

    /**
     * Returns the command.
     *
     * @param input the user's input
     * @return the command
     */
    public static String parseCommand(String input) {
        String command = input.split(" ", 2)[0];
        return command;
    }

    /**
     * Returns the tasks matching the keyword of find.
     *
     * @param input the user's input
     * @param list the list.
     * @return the matching tasks.
     * @throws FindFormatException
     */
    public static String parseFind(String input, TaskList list) throws FindFormatException {
        try {
            String content = input.substring(5);
            if (content.length() == 0) {
                throw new FindFormatException();
            } else {
                return content;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new FindFormatException();
        }
    }

    /**
     * Returns the task to be added.
     *
     * @param task the task type.
     * @param input the user's input.
     * @return the task to be added.
     * @throws AddToListException
     */
    public static Task parseTask(TaskList.TaskType task, String input) throws AddToListException {
        try {
            String[] splitContent;
            String content;
            LocalDateTime time;
            switch (task) {
            case TODO:
                content = input.substring(5);
                return new Todo(content);
            case DEADLINE:
                splitContent = parseContent(input.substring(9));
                content = splitContent[0];
                time = parseTime(splitContent[1]);
                return new Deadline(content, time);
            case EVENT:
                splitContent  = Parser.parseContent(input.substring(6));
                content = splitContent[0];
                time = parseTime(splitContent[1]);
                return new Event(content, time);
            default:
                throw new AddToListException(AddToListException.Type.FORMAT);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new AddToListException(AddToListException.Type.FORMAT);
        } catch (AddToListException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AddToListException(AddToListException.Type.FORMAT);
        }
    }
    /**
     * Parses the user's input to mark command.
     *
     * @param input the user's command.
     * @param list the list of tasks.
     * @return the index of the task.
     * @throws MarkUnmarkException
     */
    public static int parseMark(String input, TaskList list) throws MarkUnmarkException {
        try {
            int i = Integer.parseInt(input.substring(5));

            if (i > list.size() || i <= 0) {
                throw new MarkUnmarkException(MarkUnmarkException.Type.INDEX);
            }

            if (list.get(i - 1).isDone()) {
                throw new MarkUnmarkException(MarkUnmarkException.Type.MARKREPEAT);
            }

            return i;
        } catch (NumberFormatException ex) {
            // if input is mark + a non-integer,
            // throws an exception due to incorrect format
            throw new MarkUnmarkException(MarkUnmarkException.Type.MARKFORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MarkUnmarkException(MarkUnmarkException.Type.MARKFORMAT);
        }
    }

    /**
     * Parses the user's input to unmark command.
     *
     * @param input the user's command.
     * @param list the list of tasks.
     * @return the index of the task.
     * @throws MarkUnmarkException
     */
    public static int parseUnmark(String input, TaskList list) throws MarkUnmarkException {
        try {
            int i = Integer.parseInt(input.substring(7));

            if (i > list.size() || i <= 0) {
                throw new MarkUnmarkException(MarkUnmarkException.Type.INDEX);
            }

            if (!list.get(i - 1).isDone()) {
                throw new MarkUnmarkException(MarkUnmarkException.Type.UNMARKREPEAT);
            }

            return i;
        } catch (NumberFormatException ex) {
            // if input is unmark + a non-integer,
            // throws an exception due to incorrect format
            throw new MarkUnmarkException(MarkUnmarkException.Type.UNMARKFORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MarkUnmarkException(MarkUnmarkException.Type.UNMARKFORMAT);
        }
    }

    /**
     * Parses the user's input to delete command.
     *
     * @param input the user's command.
     * @param list the list of tasks.
     * @return the index of the task.
     * @throws MarkUnmarkException
     */
    public static int parseDelete(String input, TaskList list) throws DeleteException {
        try {
            int i = Integer.parseInt(input.substring(7));

            if (i > list.size() || i <= 0) {
                // the index is not within the bound of the list
                throw new DeleteException(DeleteException.Type.INDEX);
            }

            return i;
        } catch (NumberFormatException ex) {
            // if input is delete + a non-integer,
            // throws an exception due to incorrect format
            throw new DeleteException(DeleteException.Type.FORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeleteException(DeleteException.Type.FORMAT);
        }
    }
}
