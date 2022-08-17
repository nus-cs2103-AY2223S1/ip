import java.util.Scanner;

/**
 * Class to represent a parser.
 */
public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT
    }
    private static UI ui = new UI();

    /**
     * The method to mark done command task process
     * @param chat
     * @param tasklist
     * @return Task object
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
     * @return Task object
     * @throws NoDescriptionException
     * @throws NoCommandException
     */
    public static Task unmark(String chat, TaskList tasklist) throws NoDescriptionException, NoCommandException {
        int num = Integer.parseInt(chat.split(" ")[1]) - 1;
        return new Unmark(num);
    }

    /**
     * The method for Todo, Deadline, Event command task process
     * @param chat
     * @param tasklist
     * @return Task object
     * @throws NoDescriptionException
     * @throws NoCommandException
     */
    public static Task addTask(String chat, TaskList tasklist) throws NoDescriptionException, NoCommandException,
            NoTimeException {

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
                        return new Deadline(chat.substring(9).split(" /by ")[0], false,
                                chat.substring(9).split(" /by ")[1]);
                    }

                case EVENT:
                    String subString_event = chat.substring(6);
                    if (subString_event.split(" /at ").length == 1) {
                        throw new NoTimeException(command.name());
                    } else {
                        return new Event(chat.substring(6).split(" /at ")[0], false,
                                chat.substring(6).split(" /at ")[1]);
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
     * The method to parse the command.
     * @param chat
     * @param tasklist
     * @return Task object
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

                default:
                    throw new NoCommandException(chat);

            }

        } catch (NoDescriptionException | NoCommandException | NoTimeException  e) {
            e.printStackTrace();
            return null;
        }
    }
}
