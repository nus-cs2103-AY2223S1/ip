package Duke.Processor;

import Duke.Exception.NoCommandException;
import Duke.Exception.NoDescriptionException;
import Duke.Exception.NoTimeException;
import Duke.Exception.WrongTimeFormatException;
import Duke.Task.Bye;
import Duke.Task.Deadline;
import Duke.Task.Delete;
import Duke.Task.Event;
import Duke.Task.List;
import Duke.Task.Mark;
import Duke.Task.Task;
import Duke.Task.Todo;
import Duke.Task.Unmark;
import Duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent a parser.
 */
public class Parser {
    // usage of Enum
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }
    private final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static UI ui = new UI();

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

                default:
                    throw new NoCommandException(chat);

            }

        } catch (NoDescriptionException | NoCommandException | NoTimeException | WrongTimeFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}