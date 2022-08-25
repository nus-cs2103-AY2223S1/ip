package parser;

import commands.*;
import exception.InvalidDeadlineException;
import exception.MissingTaskDescriptionException;
import exception.UnknownCommandException;

import java.time.LocalDateTime;

public class Parser {

    /**
     * Returns a Command after parsing the user input
     *
     * @param userInput the user input to parse
     * @return the Command object corresponding to the parsed input
     */
    public Command parseUserInput(String userInput) throws UnknownCommandException {
        String[] deconstructedInput = userInput.trim().split("\\s+", 2);
        CommandType commandType = CommandType.valueOf(deconstructedInput[0].toUpperCase());

        switch (commandType) {

        case TODO:
            return parseTodo(deconstructedInput);

        case DEADLINE:
            return parseDeadline(deconstructedInput);

        case EVENT:
            return parseEvent(deconstructedInput);

        case LIST:
            return new ListCommand();

        case DELETE:
            return new DeleteCommand();

        case MARK:
            return new MarkCommand();

        case UNMARK:
            return new UnmarkCommand();

        case BYE:
            return new ByeCommand();

        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Returns a TodoCommand based on user input
     *
     * @param deconstructedInput The deconstructed user input.
     * @return A TodoCommand based on user input
     */
    private TodoCommand parseTodo(String[] deconstructedInput) throws MissingTaskDescriptionException {
        if (deconstructedInput.length < 2) {
            throw new MissingTaskDescriptionException();
        }
        String taskDetails = deconstructedInput[1];
        return new TodoCommand(taskDetails);
    }

    /**
     * Returns a DeadlineCommand based on user input
     *
     * @param deconstructedInput The deconstructed user input.
     * @return A DeadlineCommand based on user input
     */
    private DeadlineCommand parseDeadline(String[] deconstructedInput) throws
            MissingTaskDescriptionException, InvalidDeadlineException {
        if (deconstructedInput.length < 2) {
            throw new MissingTaskDescriptionException();
        }
        String taskDetails = deconstructedInput[1];
        String[] deconstructedDetails = taskDetails.split("\\s+(/by)\\s+", 2);
        if (deconstructedDetails.length < 2) {
            throw new InvalidDeadlineException();
        }
        String formattedDateTime;
        String inputDate = deconstructedDetails[1];
        return new DeadlineCommand(deconstructedDetails[0], formattedDateTime);
    }

    /**
     * Returns an EventCommand based on user input
     *
     * @param deconstructedInput The deconstructed user input.
     * @return An EventCommand based on user input
     */
    private EventCommand parseEvent(String[] deconstructedInput) throws MissingTaskDescriptionException {
        if (deconstructedInput.length < 2) {
            throw new MissingTaskDescriptionException();
        }
        String taskDetails = deconstructedInput[1];
    }

    /**
     * Returns a
     * @param inputDateTime
     * @return
     */
    private String parseDateTime(String inputDateTime) {
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime);

    }

}
