package duke;

import java.time.LocalDate;

/**
 * Parser scans through user input and converts it into Commands that Duke can understand.
 */
public class Parser {

    /**
     * Converts user input into Command.
     *
     * @param commandString user input that has been entered.
     * @return Command based on user input.
     * @throws DukeException the exception encountered while parsing through user input.
     */
    public Command parseCommand(String commandString) throws DukeException {
        String[] splitInput = commandString.split(" ", 2);
        switch(splitInput[0]) {
        case "bye":
            return new ExitCommand();

        case "todo":
            checkForMissingArgs(splitInput);
            return new ToDoCommand(splitInput[1]);

        case "deadline": {
            checkForMissingArgs(splitInput);
            String[] desAndDate = splitInput[1].split(" /by ");
            checkForMissingArgs(desAndDate);
            return new DeadlineCommand(desAndDate[0], LocalDate.parse(desAndDate[1]));
        }

        case "event": {
            checkForMissingArgs(splitInput);
            String[] desAndDate = splitInput[1].split(" /at ");
            checkForMissingArgs(desAndDate);
            return new EventCommand(desAndDate[0], LocalDate.parse(desAndDate[1]));
        }

        case "mark":
            checkForMissingArgs(splitInput);
            return new MarkCommand(Integer.parseInt(splitInput[1]), true);

        case "unmark":
            checkForMissingArgs(splitInput);
            return new MarkCommand(Integer.parseInt(splitInput[1]), false);

        case "list":
            return new ListCommand();

        case "find":
            checkForMissingArgs(splitInput);
            return new FindCommand(splitInput[1]);

        case "delete":
            checkForMissingArgs(splitInput);
            return new DeleteCommand(Integer.parseInt(splitInput[1]));

        default:
            throw new DukeException("Sorry nya, I don't understand what that means :3");
        }



    }

    /**
     * Checks user input for missing arguments.
     *
     * @param input user input that has been split.
     * @throws DukeException if user input is missing some arguments.
     */
    public void checkForMissingArgs(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Sorry nya! You are missing some details in your command");
        }
    }
}
