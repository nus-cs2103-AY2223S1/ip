package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateDateCommand;
import duke.command.UpdateDescriptionCommand;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;

/**
 * RequestHandler parses the user input and calls the corresponding commands.
 *
 * @author Eugene Tan
 */
public class RequestHandler {
    private enum CommandType {
        LIST, TODO, EVENT, DEADLINE, MARK, UNMARK, DELETE, BYE, FIND, UPDATE
    }

    /**
     * Returns a Command to be executed after parsing the user input.
     *
     * @param command The user input.
     * @return Command which corresponds to the user input.
     * @throws InvalidInputException If user input is invalid.
     * @throws IncompleteInputException If user input is incomplete.
     * @throws InvalidCommandException If user input is invalid command.
     */
    public static Command handleRequest(String command) throws InvalidInputException, IncompleteInputException,
            InvalidCommandException {
        String[] commandSplit = command.trim().split(" ", 2);

        try {
            CommandType commandType = CommandType.valueOf(commandSplit[0].toUpperCase());

            if (commandType == CommandType.BYE) {
                return new ByeCommand();
            } else if (commandSplit.length == 1) {
                switch (commandType) {
                case LIST:
                    return new ListCommand();
                case MARK:
                    throw new IncompleteInputException("Please key in valid index to mark");
                case UNMARK:
                    throw new IncompleteInputException("Please key in valid index to unmark");
                case TODO:
                    throw new IncompleteInputException("Please key in valid description for todo");
                case DEADLINE:
                    throw new IncompleteInputException("Please key in valid description and date for deadline");
                case EVENT:
                    throw new IncompleteInputException("Please key in valid description and date for event");
                case DELETE:
                    throw new IncompleteInputException("Please key in valid index to delete");
                case FIND:
                    throw new IncompleteInputException("Please key in a valid word to find");
                case UPDATE:
                    throw new IncompleteInputException("Please key in a valid index and description/date to update");
                default:
                    throw new InvalidCommandException("OOPS I didn't understand the command");
                }
            } else {
                String furtherSplit = commandSplit[1];

                switch (commandType) {
                case MARK: {
                    int itemNumber = Integer.parseInt(furtherSplit);
                    return new MarkCommand(itemNumber);
                }
                case UNMARK: {
                    int itemNumber = Integer.parseInt(furtherSplit);
                    return new UnmarkCommand(itemNumber);
                }
                case TODO:
                    return new TodoCommand(furtherSplit);
                case DEADLINE: {
                    String[] deadlineInput = furtherSplit.split("/by", 2);
                    if (deadlineInput.length <= 1) {
                        throw new IncompleteInputException("Please key in deadline description (/by) time ");
                    }
                    String dateToParse = deadlineInput[1].trim();
                    return new DeadlineCommand(deadlineInput[0], LocalDate.parse(dateToParse));
                }
                case EVENT: {
                    String[] eventInput = furtherSplit.split("/at", 2);
                    if (eventInput.length <= 1) {
                        throw new IncompleteInputException("Please key in event description (/at) time ");
                    }
                    String dateToParse = eventInput[1].trim();
                    return new EventCommand(eventInput[0], LocalDate.parse(dateToParse));
                }
                case DELETE: {
                    int itemNumber = Integer.parseInt(furtherSplit);
                    return new DeleteCommand(itemNumber);
                }
                case FIND:
                    String[] splitIntoInputArray = furtherSplit.split("\\s+");
                    return new FindCommand(splitIntoInputArray);
                case UPDATE: {
                    String[] splitUpdateCommand = furtherSplit.split(" ", 3);
                    int itemNumber = Integer.parseInt(splitUpdateCommand[0].trim());
                    String updateCommandType = splitUpdateCommand[1].replaceAll("\\s", "").toUpperCase();
                    if (updateCommandType.equals("DESCRIPTION")) {
                        return new UpdateDescriptionCommand(itemNumber, splitUpdateCommand[2]);
                    } else if (updateCommandType.equals("DATE")) {
                        return new UpdateDateCommand(itemNumber, LocalDate.parse(splitUpdateCommand[2]));
                    } else {
                        throw new InvalidInputException("Do you want to update description or date?"
                                + "Please key in update {INDEX} description {DESCRIPTION} "
                                + "or update {INDEX} date {yyyy-mm-dd}");
                    }

                }
                default:
                    throw new InvalidCommandException("OOPS I didn't understand the command");
                }
            }
        } catch (DateTimeParseException e) {
            throw new IncompleteInputException("Please key in a valid date (yyyy-mm-dd)");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please key in a valid positive index");
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("OOPS. I didn't understand the command. "
                    + "Please key in a valid command");
        }
    }
}
