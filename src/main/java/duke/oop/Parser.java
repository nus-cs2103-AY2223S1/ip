package duke.oop;

import duke.DukeException;
import duke.command.*;

public class Parser {
    public static Command parse(String input) throws DukeException {
        assert (input != null) : "the input is null";
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        switch (inputCommand) {
        case "bye":
            return new ExitCommand();
        case "find":
            String textToFind = input.split(" ")[1];
            return new FindCommand(textToFind);
        case "list":
            return new ListCommand();
        case "mark":
            int numToMark = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(numToMark);
        case "unmark":
            int numToUnmark = Integer.parseInt(input.split(" ")[1]);
            return new UnMarkCommand(numToUnmark);
        case "delete":
            int numToDelete = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(numToDelete);
        case "todo":
            String todoName = inputArray[1].trim();
            return new AddTodoCommand(todoName);
        case "event":
            String[] eventArr = input.replace("event ", "").split(" /at ");
            String eventName = eventArr[0];
            String eventTime = eventArr[1];
            return new AddEventCommand(eventName, eventTime);
        case "deadline":
            String[] deadlineArr = input.replace("deadline", "").split(" /by ");
            String deadlineName = deadlineArr[0];
            String deadlineTime = deadlineArr[1];
            return new AddDeadlineCommand(deadlineName, deadlineTime);
        default:
            throw new DukeException("Shindo, I am confused...");
        }
    }
}
