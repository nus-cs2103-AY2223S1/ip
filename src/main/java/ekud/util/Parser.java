package ekud.util;

import ekud.exception.EkudException;
import ekud.task.TaskList;
import ekud.task.TaskType;

import java.util.Arrays;

public class Parser {
    /**
     * Constructor that instantiates an instance of Parser.
     */
    public Parser() {

    }

    /**
     * Parses the given command.
     * 
     * @param command Command to be parsed.
     * @param taskList Task List to be modified.
     * @return ParseResult that contains whether program should be terminated, print
     *         message and whether
     *         the task list should be re-saved to storage.
     * @throws EkudException Error that occured.
     */
    public ParseResult parseCommand(String command, TaskList taskList) throws EkudException {
        String[] splitCommand = command.split(" ");
        String firstWord = splitCommand[0];
        if (command.equals("bye")) {
            return new ParseResult(true, "Bye. Hope to see you again soon!", false);
        } else if (command.equals("list")) {
            return new ParseResult(false, taskList.printTasks(), false);
        } else if (firstWord.equals("mark")) {
            return new ParseResult(false, taskList.markAsDone(command), true);
        } else if (firstWord.equals("unmark")) {
            return new ParseResult(false, taskList.markAsUndone(command), true);
        } else if (firstWord.equals("todo")) {
            return new ParseResult(false, taskList.addTask(command, TaskType.TODO), true);
        } else if (firstWord.equals("deadline")) {
            return new ParseResult(false, taskList.addTask(command, TaskType.DEADLINE), true);
        } else if (firstWord.equals("event")) {
            return new ParseResult(false, taskList.addTask(command, TaskType.EVENT), true);
        } else if (firstWord.equals("delete")) {
            return new ParseResult(false, taskList.deleteTask(command), true);
        } else if (firstWord.equals("find")) {
            return new ParseResult(false, taskList.searchTasks(
                    String.join(" ", Arrays.copyOfRange(splitCommand, 1, splitCommand.length))), false);
        } else {
            throw new EkudException("Invalid command.");
        }
    }

}
