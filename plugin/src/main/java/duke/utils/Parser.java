package duke.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.DeleteCommand;
import duke.commands.InvalidCommand;
import duke.commands.Mark;
import duke.commands.ShowList;
import duke.enums.Commands;
import duke.enums.Messages;
import duke.enums.Regex;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

public class Parser {
    private static Pattern INPUT_REGEX = Pattern.compile(Regex.INPUT_REGEX.toString());
    private TaskList tasks;

    /**
     * Initialize a command factory with the history of tasks
     * 
     * @param list
     */
    public Parser(TaskList list) {
        tasks = list;
    }

    public Command handleInput(String input) throws DukeException {
        Matcher m = INPUT_REGEX.matcher(input);
        m.find();
        String cmd = m.group(1).toUpperCase();
        String index_description = m.group(2);
        // String flag = m.group(3);
        String deadline = m.group(4);

        try {
            Commands command = Commands.valueOf(cmd);
            switch (command) {
                case TODO:
                    return new AddTodoCommand(tasks, index_description, input);
                case DEADLINE:
                    return new AddDeadlineCommand(tasks, index_description, input, deadline);
                case EVENT:
                    return new AddEventCommand(tasks, index_description, input, deadline);
                case MARK:
                    return new Mark(tasks, index_description);
                case DELETE:
                    return new DeleteCommand(tasks, index_description);
                case LIST:
                    return new ShowList(tasks);
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