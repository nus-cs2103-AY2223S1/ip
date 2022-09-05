package ava.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ava.Ui;
import ava.exception.AvaException;
import ava.exception.NoCommandException;
import ava.exception.NoDescriptionException;
import ava.exception.NoTimeException;
import ava.exception.UnknownCommandException;
import ava.exception.WrongTimeFormatException;
import ava.task.Bye;
import ava.task.Deadline;
import ava.task.Delete;
import ava.task.Event;
import ava.task.Find;
import ava.task.List;
import ava.task.Mark;
import ava.task.Task;
import ava.task.Todo;
import ava.task.Unmark;

/**
 * Utility class that handles parsing of user input to program command.
 */
public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static Ui ui = new Ui();

    /**
     * Returns a new mark task of the specified current task.
     *
     * @param chat Input from the scanner.
     * @return Task object.
     */
    public static Task mark(String chat) {
        int num = Integer.parseInt(chat.split(" ")[1]) - 1;
        return new Mark(num);
    }

    /**
     * Returns a new unmark task of the specified current task.
     *
     * @param chat Input from the scanner.
     * @return Task object.
     */
    public static Task unmark(String chat) {
        int num = Integer.parseInt(chat.split(" ")[1]) - 1;
        return new Unmark(num);
    }

    /**
     * Returns a new task of todo, deadline, or event.
     *
     * @param chat Input from the scanner.
     * @param taskList ArrayList of tasks.
     * @return Task object.
     * @throws AvaException If an exception is found.
     */
    public static Task addTask(String chat, TaskList taskList) throws AvaException {
        Commands command = Parser.Commands.valueOf(chat.toUpperCase().split(" ")[0]);
        if (chat.split(" ").length != 1) {
            switch (command) {
            case TODO:
                if (chat.split(" ").length == 1) {
                    throw new NoDescriptionException();
                } else {
                    return new Todo(chat.substring(5), false);
                }
            case DEADLINE:
                String subStringDeadline = chat.substring(9);
                if (subStringDeadline.split(" /by ").length == 1) {
                    throw new NoTimeException();
                } else {
                    try {
                        return new Deadline(subStringDeadline.split(" /by ")[0], false,
                                LocalDateTime.parse(subStringDeadline.split(" /by ")[1], TIME_FORMAT));
                    } catch (Exception e) {
                        throw new WrongTimeFormatException();
                    }
                }
            case EVENT:
                String subStringEvent = chat.substring(6);
                if (subStringEvent.split(" /at ").length == 1) {
                    throw new NoTimeException();
                } else {
                    try {
                        return new Event(subStringEvent.split(" /at ")[0], false,
                                LocalDateTime.parse(subStringEvent.split(" /at ")[1], TIME_FORMAT));
                    } catch (Exception e) {
                        throw new WrongTimeFormatException();
                    }
                }
            default:
                throw new NoCommandException();
            }
        } else {
            switch (command) {
            case TODO: case DEADLINE: case EVENT:
                throw new NoDescriptionException();
            default:
                throw new NoCommandException();
            }
        }
    }

    /**
     * Returns a new delete task of the specified current task.
     *
     * @param chat Input from the scanner.
     * @param taskList ArrayList of tasks.
     * @return Task object.
     * @throws AvaException If an exception is found.
     */
    public static Task delete(String chat, TaskList taskList) throws AvaException {
        int order = taskList.size();
        if (chat.split(" ").length == 1) {
            throw new NoDescriptionException();
        } else {
            int num = Integer.parseInt(chat.split(" ")[1]) - 1;
            return new Delete(num);
        }
    }

    /**
     * Returns a new find task to find task with the matching keyword
     *
     * @param chat Input from the scanner.
     * @param taskList ArrayList of tasks.
     * @return Find object.
     * @throws AvaException If an exception is found.
     */
    public static Find find(String chat, TaskList taskList) throws AvaException {
        if (chat.split(" ").length == 1) {
            throw new NoDescriptionException();
        } else {
            return new Find(chat.substring(5));
        }
    }

    /**
     * Parses the input and returns the task of the specified command.
     *
     * @param chat Input from the scanner.
     * @param taskList ArrayList of tasks.
     * @return Task object.
     * @throws AvaException If an exception is found.
     */
    public static Task parse(String chat, TaskList taskList) throws AvaException {

        Commands command;

        try {
            try {
                command = Parser.Commands.valueOf(chat.toUpperCase().split(" ")[0]);
            } catch (Exception e) {
                if (chat.trim().length() < 1) {
                    throw new NoCommandException();
                } else {
                    throw new UnknownCommandException(chat.toUpperCase().split(" ")[0]);
                }
            }
            switch (command) {
            case BYE:
                return new Bye();
            case LIST:
                return new List();
            case UNMARK:
                return unmark(chat);
            case MARK:
                return mark(chat);
            case TODO: case DEADLINE: case EVENT:
                return addTask(chat, taskList);
            case DELETE:
                return delete(chat, taskList);
            case FIND:
                return find(chat, taskList);
            default:
                throw new UnknownCommandException(chat);
            }
        } catch (AvaException e) {
            throw e;
        }
    }
}
