package drivers;

import commands.*;
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

    /**
     * Parse the command and calls the correct classes
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
        String body = input[1].trim();

        switch (command) {
        case END_CHAT_BOT_CMD:
            return new EndChatBotCmd();
        case LIST_USER_TEXT_CMD:
            return new ListUserTextCmd();
        case MARK_CMD:
            return new MarkTaskCmd(body);
        case UNMARK_CMD:
            return new UnmarkTaskCmd(body);
        case TODO_CMD:
            return new TodoCmd(body);
        case DEADLINE_CMD:
            return new DeadlineCmd(body);
        case EVENT_CMD:
            return new EventCmd(body);
        case DELETE_CMD:
            return new DeleteCmd(body);
        default:
            ui.readCommand(); //clear buffer
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
