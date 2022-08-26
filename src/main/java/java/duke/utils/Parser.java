
package duke.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.Mark;
import duke.commands.ShowList;
import duke.enums.Commands;
import duke.enums.Messages;
import duke.enums.Regex;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Parses command line input and outputs the corresponding command
 */
public class Parser {
    private static final Pattern INPUT_REGEX = Pattern.compile(Regex.INPUT_REGEX.toString());
    private final TaskList tasks;

    /**
     * Initialize a command factory with the history of tasks
     * @param list task list used
     */
    public Parser(TaskList list) {
        tasks = list;
    }

    /**
     * Handles the user input
     * @param input user input
     * @return Command corresponding to user input
     * @throws DukeException when there is an error
     */
    public Command handleInput(String input) throws DukeException {
        Matcher m = INPUT_REGEX.matcher(input);
        m.find();
        String cmd = m.group(1).toUpperCase();
        String indexDescription = m.group(2);
        // String flag = m.group(3);
        String deadline = m.group(4);

        try {
            Commands command = Commands.valueOf(cmd);
            switch (command) {
            case TODO:
                return new AddTodoCommand(tasks, indexDescription, input);
            case DEADLINE:
                return new AddDeadlineCommand(tasks, indexDescription, input, deadline);
            case EVENT:
                return new AddEventCommand(tasks, indexDescription, input, deadline);
            case MARK:
                return new Mark(tasks, indexDescription);
            case DELETE:
                return new DeleteCommand(tasks, indexDescription);
            case LIST:
                return new ShowList(tasks);
            case FIND:
                return new FindCommand(tasks, indexDescription);
            default:
                return new InvalidCommand(Messages.ERROR_INVALID_COMMAND.toString());
            }

        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(Messages.ERROR_INVALID_COMMAND.toString());
        }
    }
}
