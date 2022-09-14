package seedu.duke;
import seedu.duke.command.*;
import seedu.duke.exception.*;

import java.util.Arrays;
import java.util.List;

/**
 * Parser class to parse commands inputed to Duke
 */
public class Parser {
    private static final String[] COMMANDS_ARRAY =
            new String[] {"mark", "unmark", "delete", "edit", "todo", "deadline", "event", "find"};
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
        } else if (inputText.equals("coffee")) {
            return new CoffeeCommand();
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
                        throw new TooManyArgumentsException("mark");
                    }
                    return new MarkCommand(index);

                } else if (commandWord.equals("unmark")) {
                    int index;
                    try {
                        index = Integer.parseInt(details) - 1;
                    } catch (NumberFormatException e) {
                        throw new TooManyArgumentsException("unmark");
                    }
                    return new UnmarkCommand(index);

                } else if (commandWord.equals("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(details) - 1;
                    } catch (NumberFormatException e) {
                        throw new TooManyArgumentsException("delete");
                    }
                    return new DeleteCommand(index);
                } else if (commandWord.equals("edit")) {
                    String[] taskDesc = details.split(" ", 3);
                    int index;
                    if (taskDesc.length < 2) {
                        throw new NotEnoughArgumentsException();
                    } else if (taskDesc.length == 2) {
                        if (taskDesc[1].equals("name")
                                || taskDesc[1].equals("time")
                                || taskDesc[1].equals("deadline")) {
                            throw new NotEnoughArgumentsException();
                        }
                        // assume description change
                        try {
                            index = Integer.parseInt(taskDesc[0]) - 1;
                        } catch (NumberFormatException e) {
                            throw new NotNumberException();
                        }

                        return new EditNameCommand(index, taskDesc[1]);
                    } else {
                        try {
                            if (taskDesc[2].length() < 2) {
                                throw new NotEnoughArgumentsException();
                            }

                            index = Integer.parseInt(taskDesc[0]) - 1;
                            if (taskDesc[1].equals("name")) {
                                return new EditNameCommand(index, taskDesc[2]);
                            } else if (taskDesc[1].equals("time") || taskDesc.equals("deadline")) {
                                return new EditTimeCommand(index, taskDesc[2]);
                            } else { // assume edit name
                                return new EditNameCommand(index, taskDesc[1] + " " + taskDesc[2]);
                            }
                        } catch (NumberFormatException e) {
                            throw new NotNumberException();
                        }
                    }

                } else if (commandWord.equals("todo")) {
                    if (details.length() <= 1 ) {
                        throw new NotEnoughArgumentsException();
                    }

                    return new ToDoCommand(details);

                } else if (commandWord.equals("deadline")) {
                    if (details.contains(" /by ")) {
                        String[] taskDesc = details.split(" /by ");
                        if (taskDesc[0].length() <= 1) {
                            throw new NotEnoughArgumentsException();
                        }

                        return new DeadlineCommand(taskDesc[0], taskDesc[1]);
                    } else {
                        throw new NotEnoughArgumentsException();
                    }

                } else if (commandWord.equals("event")) {
                    if (details.contains(" /on ")) {
                        String[] taskDesc = details.split(" /on ");
                        if (taskDesc[0].length() <= 1) {
                            throw new NotEnoughArgumentsException();
                        }

                            return new EventCommand(taskDesc[0], taskDesc[1]);
                    } else {
                        throw new NotEnoughArgumentsException();
                    }

                } else if (commandWord.equals("find")) {
                    return new FindCommand(details);
                }
            } else {
                throw new UnrecognisedCommandException();
            }
        }
        return null;
    }

}
