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
                if (inputArr.length < 1 || inputArr[1].equals("")) {
                    throw new BlankContentException();
                }
                return new AddCommand(new ToDo(inputArr[1]));

            case ("deadline"):
                if (inputArr.length < 2) {
                    throw new BlankContentException();
                }
                String[] deadlineArr = inputArr[1].split(" /by", 2);
                if (deadlineArr.length == 1) {
                    throw new ImproperFormatException();
                }
                if (deadlineArr[1].equals("")) {
                    throw new NoDateException();
                }
                return new AddCommand(new Deadline(deadlineArr[0], deadlineArr[1]));

            case ("event"):
                if (inputArr.length < 2) {
                    throw new BlankContentException();
                }
                String[] eventArr = inputArr[1].split(" /at", 2);
                if (eventArr.length == 1) {
                    throw new ImproperFormatException();
                }
                if (eventArr[1].equals("")) {
                    throw new NoDateException();
                }
                return new AddCommand(new Event(eventArr[0], eventArr[1]));

            case ("mark"):
                if (inputArr.length < 2) {
                    throw new BlankContentException();
                }
                try {
                    int x = Integer.valueOf(inputArr[1]);
                    return new MarkStatusCommand(x);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }

            case ("unmark"):
                if (inputArr.length < 2) {
                    throw new BlankContentException();
                }
                try {
                    int x = Integer.valueOf(inputArr[1]);
                    return new UnmarkStatusCommand(x);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }

            case ("delete"):
                if (inputArr.length < 2) {
                    throw new BlankContentException();
                }
                try {
                    int x = Integer.valueOf(inputArr[1]);
                    return new DeleteCommand(x);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
            default:
                throw new CommandNotFoundException(inputKeyword);
        }
    }
}