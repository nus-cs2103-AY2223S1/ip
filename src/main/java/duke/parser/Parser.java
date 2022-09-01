package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

/**
 * A class that parse the user input into the respective commands.
 */
public class Parser {

    /**
     * Returns the type of command that is given by the user.
     * <p>
     * The function reads the first word of the input and will return the respective command types.
     *
     * @param userText the text inputted by the user
     * @return the type of command given by the user
     */
    public static Command parse(String userText) {
        String commandWord = userText.split(" ", 2)[0];
        Command command;
        if (commandWord.equals("bye")) {
            command = new ByeCommand(userText);
        } else if (commandWord.equals("list")) {
            command = new ListCommand(userText);
        } else if (commandWord.equals("mark")) {
            command = new MarkCommand(userText);
        } else if (commandWord.equals("unmark")) {
            command = new UnmarkCommand(userText);
        } else if (commandWord.equals("todo") || commandWord.equals("deadline") || commandWord.equals("event")) {
            command = new AddCommand(userText);
        } else if (commandWord.equals("delete")) {
            command = new DeleteCommand(userText);
        } else if (commandWord.equals("find")) {
            command = new FindCommand(userText);
        } else {
            command = new UnknownCommand(userText);
        }
        return command;
    }
}
