package unc;

import unc.command.*;

public class Parser {
    private enum validInput {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE

    }
    public static Command parse(String input) {
        String[] words = input.split("\\s+", 2);
        validInput verb = validInput.valueOf(words[0].toUpperCase());
        switch (verb) {
        case LIST:
            return new ListCommand();
        //break;
        case MARK:
            return new MarkCommand(Integer.parseInt(words[1]));
        //break;
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(words[1]));
        //break;
        case DELETE:
            return new DeleteCommand(Integer.parseInt(words[1]));
        //break;
        case TODO:
            return new TodoCommand(words[1]);

        //break;
        case DEADLINE:
            return new DeadlineCommand(words[1]);
        //break;
        case EVENT:
            return new EventCommand(words[1]);
        //break;
        case BYE:
            return new ExitCommand();
        default:
            throw new RuntimeException();
        }
    }
}
