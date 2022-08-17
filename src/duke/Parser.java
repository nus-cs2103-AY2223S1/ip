package duke;

import duke.exceptions.BlankCommandException;
import duke.exceptions.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String inputKeyword = inputArr[0];
        switch (inputKeyword) {
            case(""):
                throw new BlankCommandException();
            case ("bye"):
                return new ExitCommand();

            case("list"):
                return new ListCommand();
            default:
                String task = inputKeyword + " " + inputArr[1];
                return new AddCommand(task);
        }
    }
}