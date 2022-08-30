package utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TimeTaskCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.DukeException;

/**
 * Represents parser for duke
 */
public class Parser {

    /**
     * Parses the string command and returns the
     * correct command for it
     *
     * @param fullCommand full command given by user
     * @return the correct Command for the given keyword
     * @throws DukeException  If fullCommand is empty or
     * invalid full command
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand == null || fullCommand.equals("")) {
            throw new DukeException("empty command");
        }

        String[] split =  fullCommand.split(" ", 2);

        String keyword = split[0];
        

        if (keyword.equals("")) {
            throw new DukeException("empty command");
        }

        switch (keyword) {
            case "bye":
            if (split.length > 1) {
                throw new DukeException("invalid command");
            }
            return new ByeCommand();
            case "list":
            if (split.length > 1) {
                throw new DukeException("invalid command");
            }
            return new ListCommand();

            case "mark":
            if (split.length == 1) {
                throw new DukeException("empty command mark");
            }

            split = fullCommand.split(" ");
            if (split.length > 2) {
                throw new DukeException("invalid command mark");
            } else {
                boolean isNumeric = split[1].chars().allMatch(Character::isDigit);
                if (!isNumeric) {
                    throw new DukeException("invalid command mark");
                }
            }
            return new MarkCommand(Integer.parseInt(split[1]));

            case "unmark":
            if (split.length == 1) {
                throw new DukeException("empty command unmark");
            }

            split = fullCommand.split(" ");
            if (split.length > 2) {
                throw new DukeException("invalid command unmark");
            } else {
                boolean isNumeric = split[1].chars().allMatch(Character::isDigit);
                if (!isNumeric) {
                    throw new DukeException("invalid command unmark");
                }
            }
            return new UnmarkCommand(Integer.parseInt(split[1]));
            

            case "delete":
            if (split.length == 1) {
                throw new DukeException("empty command delete");
            }

            split = fullCommand.split(" ");
            if (split.length > 2) {
                throw new DukeException("invalid command delete");
            } else {
                boolean isNumeric = split[1].chars().allMatch(Character::isDigit);
                if (!isNumeric) {
                    throw new DukeException("invalid command delete");
                }
            }
            return new DeleteCommand(Integer.parseInt(split[1]));
            

            case "todo":
            if (split.length == 1) {
                throw new DukeException("empty todo");
            }
            return new TodoCommand(split[1]);

            case "deadline":
            if (split.length == 1) {
                throw new DukeException("empty deadline");
            }
            String input = split[1];

            int index = input.lastIndexOf("/by");

            if (index > -1) {
                String by = input.substring(index + 4, input.length());
                input = input.substring(1, index - 1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                boolean validTime = isValid(by, formatter);
                if (!validTime) {
                    throw new DukeException("invalid command deadline");
                }   
                return new TimeTaskCommand(input, "deadline", LocalDateTime.parse(by, formatter));
            } else {
                throw new DukeException("invalid command deadline");
            }
            

            case "event":
            if (split.length == 1) {
                throw new DukeException("empty event");
            }

            input = split[1];

            index = input.lastIndexOf("/at");

            if (index > -1) {
                String at = input.substring(index + 4, input.length());
                input = input.substring(1, index - 1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                boolean validTime = isValid(at, formatter);
                if (!validTime) {
                    throw new DukeException("invalid command event");
                }   
                return new TimeTaskCommand(input, "event", LocalDateTime.parse(at, formatter));
            } else {
                throw new DukeException("invalid command event");
            }

            default:
            throw new DukeException("invalid command");
        }
    }

    /**
     * Checks if the string representation of time is
     * in the correct format
     *
     * @param str Time we want to check
     * @param formatter format we require
     * @return boolean where the string is valid
     */
    public static boolean isValid(String str, DateTimeFormatter formatter) {
        try {
            LocalDateTime date = LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
