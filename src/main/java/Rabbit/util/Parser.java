package Rabbit.util;

import java.lang.module.FindException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import Rabbit.Task.Task;
import Rabbit.Task.Event;
import Rabbit.Task.Deadline;
import Rabbit.Task.Todo;
import Rabbit.RabbitException.AddToListException;
import Rabbit.RabbitException.MarkUnmarkException;
import Rabbit.RabbitException.DeleteException;
import Rabbit.RabbitException.ImportDataException;
import Rabbit.RabbitException.FindFormatException;

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
        char type = input.charAt(0);
        /** indicates the index of the second "|" */
        int contentBreak = 0;

        if (type == 'E' || type == 'D') {
            for (int i = 2; i < input.length(); i++) {
                if (input.charAt(i) == '|') {
                    contentBreak = i;
                }
            }

            try {
                boolean isDone = input.charAt(2) == 'X';
                String content = input.substring(4, contentBreak);
                LocalDateTime time = parseTime(input.substring(contentBreak + 1, input.length()));
                if (type == 'E') {
                    return new Event(content, time, isDone);
                }
                if (type == 'D') {
                    return new Deadline(content, time, isDone);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new ImportDataException();
            } catch (AddToListException e) {
                throw new ImportDataException();
            }
        } else if (type == 'T') {
            boolean isDone = input.charAt(2) == 'X';
            String content = input.substring(4, input.length());
            return new Todo(content, isDone);
        } else {
            throw new ImportDataException();
        }
        return new Todo("");
    }

    /**
     * Returns localDataTime according to the input.
     *
     * @param input the time input from the user.
     * @return a LocalDataTime instance.
     */
    public static LocalDateTime parseTime(String input) throws AddToListException {
        try {
            int year = Integer.parseInt(input.substring(0,4));
            int month = Integer.parseInt(input.substring(5,7));
            int day = Integer.parseInt(input.substring(8,10));
            int hour = Integer.parseInt(input.substring(11,13));
            int minute = Integer.parseInt(input.substring(13,15));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException e) {
            // if input is delete + a non-integer,
            // throws an exception due to incorrect format
            throw new AddToListException(AddToListException.Type.FORMAT);
        } catch (DateTimeException e) {
            throw new AddToListException(AddToListException.Type.TIMEFORMAT);
        }
    }

    /**
     * Returns the index that separates the content
     * from the time of the task when the user creates a task.
     *
     * @param input the string after the task type in the input.
     * @return the index of the character in the string before which.
     * is the content, after which is the time, -1 if the format is wrong.
     */
    public static int parseContent(String input) {
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == ' ' && input.charAt(i + 1) == '/') {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Returning the first word of the string.
     *
     * @param input the user's input
     * @return the index of the character in the string
     * before which is the first word
     */
    public static int parseFunction(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                return i + 1;
            }
        }
        return input.length();
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
            }
            return content;
        } catch (StringIndexOutOfBoundsException e) {
            throw new FindFormatException();
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
            Integer.parseInt(input.substring(5));
        } catch (NumberFormatException ex) {
            // if input is mark + a non-integer,
            // throws an exception due to incorrect format
            throw new MarkUnmarkException(MarkUnmarkException.Type.MARKFORMAT);
        }

        int i = Integer.parseInt(input.substring(5));

        if (i > list.size() || i <= 0) {
            throw new MarkUnmarkException(MarkUnmarkException.Type.INDEX);
        }

        if (list.get(i - 1).isDone()) {
            throw new MarkUnmarkException(MarkUnmarkException.Type.MARKREPEAT);
        }

        return i;
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
            Integer.parseInt(input.substring(7));
        } catch (NumberFormatException ex) {
            // if input is unmark + a non-integer,
            // throws an exception due to incorrect format
            throw new MarkUnmarkException(MarkUnmarkException.Type.UNMARKFORMAT);
        }

        int i = Integer.parseInt(input.substring(7));
        if (i > list.size() || i <= 0) {
            throw new MarkUnmarkException(MarkUnmarkException.Type.INDEX);
        }

        if (!list.get(i - 1).isDone()) {
            throw new MarkUnmarkException(MarkUnmarkException.Type.UNMARKREPEAT);
        }

        return i;
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
            Integer.parseInt(input.substring(7));
        } catch (NumberFormatException ex) {
            // if input is delete + a non-integer,
            // throws an exception due to incorrect format
            throw new DeleteException(DeleteException.Type.FORMAT);
        }

        int i = Integer.parseInt(input.substring(7));
        if (i > list.size() || i <= 0) {
            // the index is not within the bound of the list
            throw new DeleteException(DeleteException.Type.INDEX);
        }

        return i;
    }
}
