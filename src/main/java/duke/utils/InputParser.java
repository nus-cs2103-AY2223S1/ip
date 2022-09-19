package duke.utils;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.FindTaskCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.PrintTasksCommand;
import duke.commands.UndoCommand;
import duke.commands.UnmarkTaskCommand;
import duke.commands.UnrecognisedCommand;
import duke.tasks.TaskList;
import duke.tasks.TaskType;

import java.util.Deque;

/**
 * This class is responsible for parsing the user input.
 */
public class InputParser {

    /**
     * Parses the input string.
     * @param input Input string.
     * @param tasks TaskList in use by the app.
     * @param storage Storage in use by the app.
     * @return Command to be executed.
     */
    public Command parse(String input, TaskList tasks, Storage storage, Deque<Command> commandHistory) {
        String[] splitStr = splitInput(input);
        String command = splitStr[0];
        String body = splitStr[1];
        return parseCommand(command, body, tasks, storage, commandHistory);
    }

    private Command parseCommand(String command, String body, TaskList tasks, Storage storage,
            Deque<Command> commandHistory) {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintTasksCommand(tasks);
        case "mark":
            return new MarkTaskCommand(storage, tasks, body);
        case "unmark":
            return new UnmarkTaskCommand(storage, tasks, body);
        case "delete":
            return new DeleteTaskCommand(storage, tasks, body);
        case "find":
            return new FindTaskCommand(tasks, body);
        case "undo":
            return new UndoCommand(commandHistory);
        case "todo":
            return new AddTaskCommand(storage, tasks, TaskType.TODO, body);
        case "deadline":
            return new AddTaskCommand(storage, tasks, TaskType.DEADLINE, body);
        case "event":
            return new AddTaskCommand(storage, tasks, TaskType.EVENT, body);
        default:
            return new UnrecognisedCommand(command);
        }
    }

    private String[] splitInput(String input) {
        String[] ret = new String[2];

        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            ret[0] = input;
            ret[1] = "";
        } else {
            ret[0] = input.substring(0, spaceIndex);
            ret[1] = input.substring(spaceIndex + 1);
        }

        return ret;
    }

}
