package duke.utils;

import duke.excecption.EmptyInputException;
import duke.task.TaskList;

/**
 * Class for parsing strings into commands.
 */
public class Parser {

    /**
     * Parses a string into a command.
     * @param line - the string to parse
     * @param todolist - the task list to modify
     * @return 1 if the user wants to quit, 0 otherwise
     */
    public int parse(String line, TaskList todolist) {
        switch (line) {
        case "":
            throw new EmptyInputException();

        case "bye":
            String response = execute(line, todolist);
            System.out.println(response);
            return 1;

        default:
            response = execute(line, todolist);
            System.out.println(response);
            break;
        }
        return 0;
    }

    /**
     * Executes a command.
     * @param line - the command to execute
     * @param todolist - the task list to modify
     * @return the response to the command
     */
    public String execute(String line, TaskList todolist) {
        switch (line) {
        case "":
            throw new EmptyInputException();

        case "bye":
            return "See you again!";

        case "list":
            return todolist.getData();

        case "save":
            Storage.save(todolist);
            return "Saved!";

        default:

            // We can't use switch statements since we want regex matching
            String action = ActionParser.parse(line, todolist);
            if (!action.equals("")) {
                return action;
            }

            return TaskParser.parse(line, todolist);
        }
    }
}
