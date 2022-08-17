package duke;

import duke.exceptions.BlankCommandException;
import duke.exceptions.DukeException;
import duke.exceptions.BlankContentException;

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

            case ("mark"):

            case ("unmark"):
                if (inputArr.length < 2) {
                    throw new BlankContentException("TRYING TO " + inputKeyword.toUpperCase() + " A TASK!");
                }
                return new ToggleStatusCommand(inputArr[1]);
            default:
                if (inputArr.length == 1) {
                    return new AddCommand(inputKeyword);
                }
                String task = inputKeyword + " " + inputArr[1];
                return new AddCommand(task);
        }
    }
}