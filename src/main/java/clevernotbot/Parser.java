package clevernotbot;

import command.*;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Makes sense out of user command.
     *
     * @param text User command.
     * @return A runnable command.
     */
    public Command parseText(String text) {
        String[] txtArr = text.split(" ");
        switch (txtArr[0].toLowerCase()) {
        case "find":
            return new FindCommand(text, false);
        case "mark":
            return new MarkCommand(text, false);
        case "unmark":
            return new UnmarkCommand(text, false);
        case "list":
            return new ListCommand(text, false);
        case "todo":
            return new AddToDoCommand(text, false);
        case "deadline":
            return new AddDeadLineCommand(text, false);
        case "event":
            return new AddEventCommand(text, false);
        case "delete":
            return new DeleteCommand(text, false);
        case "greet":
            return new GreetCommand(text, false);
        case "bye":
            return new ByeCommand(text, true);
        case "undo":
            return new UndoCommand(text,true);
        default:
            return new DefaultCommand(text, false);
        }
    }

}

