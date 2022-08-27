package duke;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnMarkCommand;
import dukeexceptions.DukeException;
import dukeexceptions.NoDescriptionException;
import dukeexceptions.NoSuchCommandException;

public class Parser {
    public enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String input) throws DukeException {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length == 1) {
            switch (Commands.valueOf(inputSplit[0].strip().toUpperCase())) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case UNMARK:
                throw new NoDescriptionException("unmark");
            case DEADLINE:
                throw new NoDescriptionException("deadline");
            case DELETE:
                throw new NoDescriptionException("delete");
            case EVENT:
                throw new NoDescriptionException("event");
            case MARK:
                throw new NoDescriptionException("mark");
            case TODO:
                throw new NoDescriptionException("todo");
            default:
                throw new NoSuchCommandException();
            }
        } else {
            switch (Commands.valueOf(inputSplit[0].strip().toUpperCase())) {
            case UNMARK:
                int indUnmark = Integer.parseInt(inputSplit[1]) - 1;
                return new UnMarkCommand(indUnmark);
            case MARK:
                int indMark = Integer.parseInt(inputSplit[1]) - 1;
                return new MarkCommand(indMark);
            case DEADLINE:
                String[] descriptDate = inputSplit[1].split("/by", 2);
                return new DeadlineCommand(descriptDate[0], descriptDate[1]);
            case EVENT:
                String[] descriptTime = inputSplit[1].split("/at", 2);
                return new EventCommand(descriptTime[0], descriptTime[1]);
            case TODO:
                String des = inputSplit[1];
                return new TodoCommand(des);
            case DELETE:
                int del = Integer.parseInt(inputSplit[1]) - 1;
                return new DeleteCommand(del);
            default:
                throw new NoSuchCommandException();
            }
        }
    }
}

