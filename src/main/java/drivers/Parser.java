package drivers;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EndChatBotCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListUserTextCommand;
import commands.MarkTaskCommand;
import commands.SortCommand;
import commands.TodoCommand;
import commands.UnmarkTaskCommand;
import exceptions.TumuException;
import exceptions.UnrecognisedCommandException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static final String END_CHAT_BOT_CMD = "bye";
    private static final String LIST_USER_TEXT_CMD = "list";
    private static final String MARK_CMD = "mark";
    private static final String UNMARK_CMD = "unmark";
    private static final String TODO_CMD = "todo";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";
    private static final String DELETE_CMD = "delete";
    private static final String FIND_CMD = "find";
    private static final String SORT_CMD = "sort";

    /**
     * Parses the command and calls the correct classes
     * to give the appropriate output.
     * @param fullCommand Full command given by the user.
     * @param ui Specifies how the program interacts with the user.
     * @return The command class to be executed.
     * @throws TumuException Parent exception for the program.
     * @throws NumberFormatException Exception is thrown when the String cannot be parsed
     *                               into an integer.
     */
    public static Command parse(String fullCommand, Ui ui) throws TumuException, NumberFormatException {
        String[] input = fullCommand.split(" ", 2);
        String command = input[0].trim().toLowerCase();
        String body = input.length > 1 ? input[1].trim() : null;

        switch (command) {
        case END_CHAT_BOT_CMD:
            return new EndChatBotCommand();
        case LIST_USER_TEXT_CMD:
            return new ListUserTextCommand();
        case MARK_CMD:
            return new MarkTaskCommand(body);
        case UNMARK_CMD:
            return new UnmarkTaskCommand(body);
        case TODO_CMD:
            return new TodoCommand(body);
        case DEADLINE_CMD:
            return new DeadlineCommand(body);
        case EVENT_CMD:
            return new EventCommand(body);
        case DELETE_CMD:
            return new DeleteCommand(body);
        case FIND_CMD:
            return new FindCommand(body);
        case SORT_CMD:
            return new SortCommand();
        default:
            throw new UnrecognisedCommandException(command);
        }
    }

    /**
     * Gets the keyword command eg "deadline", "event" from the full command
     * given by the user.
     * @param fullCommand Full command given by the user.
     * @return String containing the keyword command issued by the user.
     */
    public static String getCommand(String fullCommand) {
        String[] input = fullCommand.split(" ", 2);
        return input[0].trim().toLowerCase();
    }
}
