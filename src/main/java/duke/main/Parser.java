package duke.main;

import duke.commandword.CommandWord;
import duke.exception.DukeException;
/**
 * Class dealing with logic of user commands.
 */
public class Parser {
    private static CommandWord command; // Command word
    private static String description; // Description field

    /**
     * Parses input string, updates command word and task description if they are valid.
     *
     * @param input Input string to be parsed.
     * @throws DukeException If command or input description is invalid.
     */
    public static void parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        try {
            String inputCommand = inputArr[0];
            command = CommandWord.getCommand(inputCommand); // Throws DE
            // If the command word is not LIST or BYE, check if user input is valid.
            if (command != CommandWord.LIST && command != CommandWord.BYE) {
                String inputDescription = inputArr[1]; // Throws AIOOBE
                checkDescription(inputDescription); // Throws DE
                description = inputDescription;
            }
        } catch (DukeException de) {
            throw de;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Hmm... Seems like you did not add a task description or number!");
        }
    }

    /**
     * Checks if user input task description is valid corresponding to the command word.
     * @param description Input task description.
     * @throws DukeException If deadline/time/index fields are invalid.
     */
    public static void checkDescription(String description) throws DukeException {
        switch (command) {
        case DEADLINE: {
            checkDeadline(description);
            break;
        }
        case EVENT: {
            checkTime(description);
            break;
        }
        case MARK:
        case UNMARK:
        case DELETE: {
            checkIndex(description);
            break;
        }
        case NOTE: {
            checkNote(description);
            break;
        }
        default: {
            break;
        }
        }
    }

    /**
     * Checks deadline field of Deadline objects.
     * @param description Description field of given Deadline object.
     * @throws DukeException If deadline does not exist or in the wrong format.
     */
    public static void checkDeadline(String description) throws DukeException {
        String[] descriptionArr = description.split(" /by ");
        try {
            String taskName = descriptionArr[0];

            String taskDeadline = descriptionArr[1]; // Throws AIOOBE
            DateTime.parseDate(taskDeadline); // Throws DukeException
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please indicate the date & time of this deadline using /by !");
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Checks time field of Event objects.
     * @param description Description field of given Event object.
     * @throws DukeException If time does not exist or in the wrong format.
     */
    public static void checkTime(String description) throws DukeException {
        String[] descriptionArr = description.split(" /at ");
        try {
            String taskName = descriptionArr[0];

            String taskDeadline = descriptionArr[1]; // Throws AIOOBE
            DateTime.parseDate(taskDeadline); // Throws DukeException
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please indicate the date & time of this event using /at !");
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Checks if index given by user is valid.
     * @param description Description field of MARK/UNMARK/DELETE command calls.
     * @throws DukeException If the given task number is invalid.
     */
    public static void checkIndex(String description) throws DukeException {
        try {
            Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("Hmm... Did you add a wrong task number?");
        }
    }

    /**
     * Checks if the index given by user is valid.
     * @param description Description field of NOTE command calls.
     * @throws DukeException If the given task number is invalid
     */
    public static void checkNote(String description) throws DukeException {
        String[] descriptionArr = description.split(" ", 2);
        try {
            String taskIndex = descriptionArr[0];
            String taskNote = descriptionArr[1]; // Throws AIOOBE
            checkIndex(taskIndex); // Throws DukeException
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please write the note you want to add!");
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Returns the CommandWord.
     * @return CommandWord command of this class.
     */
    public static CommandWord getCommand() {
        assert command != null : "Command does not exist!";
        return command;
    }

    /**
     * Returns the description string.
     * @return Description string of this class.
     */
    public static String getDescription() {
        assert description != null : "Description does not exist!";
        return description;
    }
}

