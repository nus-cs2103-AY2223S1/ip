import java.time.LocalDate;
public class Parser {

    public Command parseCommand(String CommandString) throws DukeException {
        String[] splitInput = CommandString.split(" ", 2);
        switch(splitInput[0]) {
        case "bye" :
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

        case "delete":
            checkForMissingArgs(splitInput);
            return new DeleteCommand(Integer.parseInt(splitInput[1]));

        default :
            throw new DukeException("Sorry nya, I don't understand what that means :3");
        }



    }

    public void checkForMissingArgs(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Sorry nya! You are missing some details in your command");
        }
    }
}
