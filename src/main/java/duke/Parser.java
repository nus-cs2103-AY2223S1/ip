package duke;

import duke.command.*;

public class Parser {
    public static Command parse(String userInput) throws DukeException{
        String command;
        String taskDetails;

        if (userInput.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The user input cannot be empty.");
        }
        command = userInput.split(" ")[0];
        if (command.equals(Duke.Keyword.EXIT.getKeyword())) {
            return new ExitCommand();
        } else if (command.equals(Duke.Keyword.LIST.getKeyword())) {
            return new ListCommand();
        } else if (command.equals(Duke.Keyword.MARK.getKeyword()) || command.equals(Duke.Keyword.UNMARK.getKeyword())) {
            return new MarkCommand(userInput, command.equals(Duke.Keyword.MARK.getKeyword()) ? true : false);
        } else if (command.equals(Duke.Keyword.TODO.getKeyword())) {
            return new AddCommand(userInput);
        } else if (command.equals(Duke.Keyword.EVENT.getKeyword())) {
            return new AddCommand(userInput);
        } else if (command.equals(Duke.Keyword.DEADLINE.getKeyword())) {
            return new AddCommand(userInput);
        } else if (command.equals(Duke.Keyword.DELETE.getKeyword())) {
            return new DeleteCommand(userInput);
        } else if (command.equals(Duke.Keyword.FIND.getKeyword())) {
            return new FindCommand(userInput);
        }else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
