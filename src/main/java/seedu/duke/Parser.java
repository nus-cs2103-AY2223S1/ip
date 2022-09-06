package seedu.duke;
import seedu.duke.command.*;

import java.util.Arrays;
import java.util.List;

/**
 * Parser class to parse commands inputed to Duke
 */
public class Parser {
    private static final String[] COMMANDS_ARRAY =
            new String[] {"mark", "unmark", "delete", "todo", "deadline", "event", "find"};
    private static final List<String> COMMANDS = Arrays.asList(COMMANDS_ARRAY);

    /**
     * Parses the given command and returns the appropriate command to be executed.
     * Throws an exception if command is given in the wrong format.
     * @param inputText whatever the use has inputed
     * @return relevant command
     * @throws DukeException
     */
    public static Command parse(String inputText) throws DukeException {
        inputText = inputText.toLowerCase();
        if (inputText.equals("bye")) {
            return new ExitCommand();
        } else if (inputText.equals("list")) {
            return new ListCommand();
        } else {
            String[] splitInput = inputText.split(" ", 2);
            if (splitInput.length == 2 && COMMANDS.contains(splitInput[0])) {
                String commandWord = splitInput[0];
                String details = splitInput[1];
                if (commandWord.equals("mark")) {
                    int index;
                    try {
                        index = Integer.parseInt(details) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please input only 'mark' and a number, Master.");
                    }
                    return new MarkCommand(index);

                } else if (commandWord.equals("unmark")) {
                    int index;
                    try {
                        index = Integer.parseInt(details) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please input only 'unmark' and a number, Master.");
                    }
                    return new UnmarkCommand(index);

                } else if (commandWord.equals("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(details) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please input only 'delete' and a number, Master.");
                    }
                    return new DeleteCommand(index);

                } else if (commandWord.equals("todo")) {
                    return new ToDoCommand(details);

                } else if (commandWord.equals("deadline")) {
                    if (details.contains(" /by ")) {
                        String[] taskDesc = details.split(" /by ");
                        return new DeadlineCommand(taskDesc[0], taskDesc[1]);
                    } else {
                        throw new DukeException("I'll need more information than that, Master.");
                    }

                } else if (commandWord.equals("event")) {
                    if (details.contains(" /on ")) {
                        String[] taskDesc = details.split(" /on ");
                            return new EventCommand(taskDesc[0], taskDesc[1]);
                    } else {
                        throw new DukeException("I'll need more information than that, Master.");
                    }

                } else if (commandWord.equals("find")) {
                    return new FindCommand(details);
                }
            } else {
                throw new DukeException("Sorry, Master, I don't understand what that means.");
            }
        }
        return null;
    }

}
