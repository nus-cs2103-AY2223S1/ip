package duke.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AbstractCommand;
import duke.commands.AddDeadline;
import duke.commands.AddEvent;
import duke.commands.AddTodo;
import duke.commands.Delete;
import duke.commands.InvalidCommand;
import duke.commands.Mark;
import duke.commands.ShowList;
import duke.enums.Commands;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

public class CommandFactory {
    private static Pattern INPUT_REGEX = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: ([^/]*))?$");
    private TaskList tasks;

    /**
     * Initialize a command factory with the history of tasks
     * 
     * @param list
     */
    public CommandFactory(TaskList list) {
        tasks = list;
    }

    public AbstractCommand handleInput(String input) throws DukeException {
        Matcher m = INPUT_REGEX.matcher(input);
        m.find();
        String cmd = m.group(1).toUpperCase();
        String index_description = m.group(2);
        String flag = m.group(3);
        String deadline = m.group(4);

        try {
            Commands command = Commands.valueOf(cmd);
            switch (command) {
                case TODO:
                    return new AddTodo(tasks, index_description);
                case DEADLINE:
                    return new AddDeadline(tasks, index_description, deadline);
                case EVENT:
                    return new AddEvent(tasks, index_description, deadline);
                case MARK:
                    return new Mark(tasks, index_description);
                case DELETE:
                    return new Delete(tasks, index_description);
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