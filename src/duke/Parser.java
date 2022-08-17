package duke;

import duke.exceptions.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String inputKeyword = inputArr[0];
        if (inputKeyword.equals("bye")) {
            return new ExitCommand();
        } else {
            return new EchoCommand(inputKeyword);
        }
    }
}