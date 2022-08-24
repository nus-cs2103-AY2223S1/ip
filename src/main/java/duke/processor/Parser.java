package duke.processor;

import duke.exception.NoCommandException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;
import duke.exception.WrongTimeFormatException;
import duke.task.Bye;
import duke.task.Deadline;
import duke.task.Delete;
import duke.task.Event;
import duke.task.Find;
import duke.task.List;
import duke.task.Mark;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Unmark;
import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent a parser.
 */
public class Parser {
    // usage of Enum
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }
    private final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static Ui ui = new Ui();

    /**
     * The method to mark done command task process
     * @param chat
     * @param tasklist
     * @return Duke.Task.Task object
     * @throws NoDescriptionException
     * @throws NoCommandException
     */
    public static Task mark(String chat, TaskList tasklist) throws NoDescriptionException, NoCommandException {
        int num = Integer.parseInt(chat.split(" ")[1]) - 1;
        return new Mark(num);
    }

    /**
     * The method to mark undone command task process
     * @param chat
     * @param tasklist
     * @return Duke.Task.Task object
     * @throws NoDescriptionException
     * @throws NoCommandException
     */
    public static Task unmark(String chat, TaskList tasklist) throws NoDescriptionException, NoCommandException {
        int num = Integer.parseInt(chat.split(" ")[1]) - 1;
        return new Unmark(num);
    }

    /**
     * The method for Duke.Task.Todo, Duke.Task.Deadline, Duke.Task.Event command task process
     * @param chat
     * @param tasklist
     * @return Duke.Task.Task object
     * @throws NoDescriptionException
     * @throws NoCommandException
     */
    public static Task addTask(String chat, TaskList tasklist) throws NoDescriptionException, NoCommandException,
            NoTimeException, WrongTimeFormatException {

        Commands command = Parser.Commands.valueOf(chat.toUpperCase().split(" ")[0]);
        if (chat.split(" ").length != 1) {
            switch (command) {
                case TODO:
                    if (chat.split(" ").length == 1) {
                        throw new NoDescriptionException(command.name());
                    } else {
                        return new Todo(chat.substring(5), false);
                    }

                case DEADLINE:
                    String subString_deadline = chat.substring(9);
                    if (subString_deadline.split(" /by ").length == 1) {
                        throw new NoTimeException(command.name());
                    } else {
                        try {
                            return new Deadline(subString_deadline.split(" /by ")[0], false,
                                    LocalDateTime.parse(subString_deadline.split(" /by ")[1], timeFormat));
                        } catch (Exception e ) {
                            throw new WrongTimeFormatException();
                        }
                    }

                case EVENT:
                    String subString_event = chat.substring(6);
                    if (subString_event.split(" /at ").length == 1) {
                        throw new NoTimeException(command.name());
                    } else {
                        try{
                            return new Event(subString_event.split(" /at ")[0], false,
                                    LocalDateTime.parse(subString_event.split(" /at ")[1], timeFormat));
                        } catch (Exception e) {
                            throw new WrongTimeFormatException();
                        }
                    }

                default:
                    throw new NoCommandException(command.name());
            }
        }

        else {
            switch (command) {
                case TODO:
                    throw new NoDescriptionException(command.name());
                case DEADLINE:
                    throw new NoDescriptionException(command.name());
                case EVENT:
                    throw new NoDescriptionException(command.name());
                default:
                    throw new NoCommandException(command.name());
            }
        }
    }

    /**
     * The method to delete task.
     * @param tasklist
     * @param chat
     * @return Duke.Task.Task object
     * @throws NoDescriptionException
     * @throws NoCommandException
     */
    public static Task delete(String chat, TaskList tasklist) throws NoDescriptionException {

        int order = tasklist.size();
        if (chat.split(" ").length == 1)  {
            throw new NoDescriptionException("Duke.Task.Delete");
        } else {
            int num = Integer.parseInt(chat.split(" ")[1]) - 1;
            return new Delete(num);
        }
    }

    /**
     * Returns a new find task to find task with the matching keyword
     *
     * @param chat Input from the scanner.
     * @param tasklist ArrayList of tasks.
     * @return Duke.Task.Find object
     * @throws NoDescriptionException If there are no description.
     */
    public static Find find(String chat, TaskList tasklist) throws NoDescriptionException {
        if (chat.split(" ").length == 1) {
            throw new NoDescriptionException("Find");
        } else {
            return new Find(chat.substring(5));
        }
    }

    /**
     * The method to parse the command.
     * @param chat
     * @param tasklist
     * @return Duke.Task.Task object
     */
    public static Task parse(String chat, TaskList tasklist) {

        Commands command;

        try {
            try {
                command = Parser.Commands.valueOf(chat.toUpperCase().split(" ")[0]);
            } catch (Exception e){
                throw new NoCommandException(chat);
            }
            switch (command) {
                case BYE:
                    return new Bye();

                case LIST:
                    return new List();

                case UNMARK:
                    return unmark(chat, tasklist);

                case MARK:
                    return mark(chat, tasklist);

                case TODO:
                    return addTask(chat, tasklist);

                case DEADLINE:
                    return addTask(chat, tasklist);

                case EVENT:
                    return addTask(chat, tasklist);

                case DELETE:
                    return delete(chat, tasklist);

                case FIND:
                    return find(chat, tasklist);

                default:
                    throw new NoCommandException(chat);

            }

        } catch (NoDescriptionException | NoCommandException | NoTimeException | WrongTimeFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}