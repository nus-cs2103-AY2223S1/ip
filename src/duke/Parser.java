package duke;

import duke.exceptions.*;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String inputKeyword = inputArr[0];
        switch (inputKeyword) {
            case (""):
                throw new BlankCommandException();
            case ("bye"):
                return new ExitCommand();

            case ("list"):
                return new ListCommand();

            case ("todo"):
                if (inputArr.length == 1) {
                    return new AddCommand(new ToDo(inputKeyword));
                }
                String task = inputKeyword + " " + inputArr[1];
                return new AddCommand(new ToDo(inputKeyword));

            case ("deadline"):
                String[] deadlineArr = inputArr[1].split(" /by", 2);
                if (deadlineArr.length == 1) {
                    throw new ImproperFormatException();
                }
                if (deadlineArr[1].equals("")) {
                    throw new NoDateException();
                }
                return new AddCommand(new Deadline(deadlineArr[0], deadlineArr[1]));

            case ("event"):
                String[] eventArr = inputArr[1].split(" /at", 2);
                if (eventArr.length == 1) {
                    throw new ImproperFormatException();
                }
                if (eventArr[1].equals("")) {
                    throw new NoDateException();
                }
                return new AddCommand(new Event(eventArr[0], eventArr[1]));

            case ("mark"):

            case ("unmark"):
                if (inputArr.length < 2) {
                    throw new BlankContentException("TRYING TO " + inputKeyword.toUpperCase() + " A TASK!");
                }
                return new ToggleStatusCommand(inputArr[1]);
            default:
                throw new CommandNotFoundException(inputKeyword);
        }
    }
}