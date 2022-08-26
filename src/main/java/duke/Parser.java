package duke;

import commands.*;
import dukeexceptions.DukeException;
import dukeexceptions.NoDescriptionException;
import dukeexceptions.NoSuchCommandException;

public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    /**
     * Returns Command that program is to execute from String input.
     * @param input String to be converted to command.
     * @return Command for program to execute.
     * @throws DukeException If an illegal command is entered or not enought information was given.
     */
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
            case FIND:
                throw new NoDescriptionException("find");
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
            case FIND:
                String keyword = inputSplit[1];
                return new FindCommand(keyword);
            default:
                throw new NoSuchCommandException();
            }
            }

        }
    }

