package duke;

import duke.command.*;

import java.util.ArrayList;
import java.util.List;

/** A class that deals with making sense of the user input, and translating it to a command for the bot to execute. */
public class Parser {

    private static final ArrayList<String> ADD_COMMANDS = new ArrayList<>(List.of("todo", "deadline", "event"));
    private static final ArrayList<String> DELETE_COMMANDS = new ArrayList<>(List.of("delete", "remove"));
    private static final ArrayList<String> EDIT_COMMANDS = new ArrayList<>(List.of("mark", "unmark", "update"));
    private static final ArrayList<String> VIEW_COMMANDS = new ArrayList<>(List.of("list", "find"));
    private static final ArrayList<String> EXIT_COMMANDS = new ArrayList<>(List.of("bye"));
    private static final ArrayList<String> UPDATE_COMMANDS = new ArrayList<>(List.of("update"));

    /**
     * Parses the user input into a command for the bot.
     *
     * @param strToParse The user input.
     * @return A Command that can be executed by the bot.
     * @throws DukeException If the user gives an invalid input that the bot cannot understand.
     */
    public static Command parse(String strToParse) throws DukeException {
        String[] splitTask = strToParse.split(" ", 2);
        String keyword = splitTask[0];
        if (!ADD_COMMANDS.contains(keyword) && !EDIT_COMMANDS.contains(keyword) && !VIEW_COMMANDS.contains(keyword)
                && !EXIT_COMMANDS.contains(keyword) && !DELETE_COMMANDS.contains(keyword)
                        && !UPDATE_COMMANDS.contains(keyword)) {
            throw new DukeException("oops, I am unable to understand your command :(");
        } else {
            if (EXIT_COMMANDS.contains(keyword)) {
                return new ExitCommand();
            } else if (VIEW_COMMANDS.contains(keyword)) {
                return new ViewCommand(keyword, strToParse);
            } else {
                try {
                    String input = splitTask[1];
                    if (ADD_COMMANDS.contains(keyword)) {
                        return new AddCommand(keyword, input);
                    } else if (EDIT_COMMANDS.contains(keyword)) {
                        return new EditCommand(keyword, input);
                    } else if (DELETE_COMMANDS.contains(keyword)) {
                        return new DeleteCommand(input);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("oops, your command seems to be incomplete :(");
                }
            }
        }
        return null;
    }
}
