package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parsers user inputs of the duke program.
 */
public class Parser {
    /**
     * Parses user input, identifying the command to run and returns that command.
     *
     * @param userInput The command string the user has submitted
     * @return A command based on the user's input
     * @throws DukeException If the user input is invalid and a command can't be instantiated
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            return new ListTasksCommand();
        } else if (userInput.matches("mark \\d+")) {
            int taskNumber = Integer.parseInt(userInput.substring(5));
            return new MarkTaskDoneCommand(taskNumber);
        } else if (userInput.matches("unmark \\d+")) {
            int taskNumber = Integer.parseInt(userInput.substring(7));
            return new MarkTaskNotDoneCommand(taskNumber);
        } else if (userInput.matches("delete \\d+")) {
            int taskNumber = Integer.parseInt(userInput.substring(7));
            return new DeleteTaskCommand(taskNumber);
        } else if (userInput.matches("find .+")) {
            return new FindTaskCommand(userInput.substring(5));
        } else if (userInput.matches("((?i)^(todo)(.*))|((?i)^(deadline)(.*))|((?i)^(event)(.*))")) {
            return new AddTaskCommand(userInput);
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException("Hm...Duke doesn't understand what that means :(");
        }
    }
}
