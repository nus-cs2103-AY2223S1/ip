package duke;

import duke.gui.Gui;

import java.io.IOException;
import java.util.Arrays;

/**
 * The Parser class parses any command inputted by the user
 */
public class Parser {

    /**
     * A constructor for the Parser class
     *
     * @param input The command inputted by the user
     * @param tl The <code>TaskList</code> given to manage
     * @return A boolean indicating whether the program is to be closed
     * @throws DukeException If there is a Duke-specific error encountered
     * @throws IOException If the input cannot be handled by the parser
     */
    public static String parse(String input, TaskList tl) throws DukeException, IOException {
        String[] inputArray = input.split(" ");
        String response = null;

        switch (inputArray[0].toUpperCase()) {
        case "LIST":
            response = tl.listTask();
            break;
        case "MARK":
            if (inputArray.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            int taskNum = Integer.parseInt(inputArray[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            response = tl.markTask(taskNum);
            break;
        case "UNMARK":
            if (inputArray.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            taskNum = Integer.parseInt(inputArray[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            response = tl.unmarkTask(taskNum);
            break;
        case "TODO":
            if (inputArray.length < 2) {
                throw new DukeException("The description of a Todo task cannot be empty");
            }
            String[] taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            response = tl.createToDo(taskDesc);
            break;
        case "DEADLINE":
            taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            taskDesc = String.join(" ", taskDesc).split("/by ");
            if (taskDesc.length < 2) {
                throw new DukeException("You're missing some details!\nRemember to include the description "
                        + "followed by '/by [INSERT DATETIME]'");
            }
            response = tl.createDeadline(taskDesc);
            break;
        case "EVENT":
            taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            taskDesc = String.join(" ", taskDesc).split("/at ");
            if (taskDesc.length < 2) {
                throw new DukeException("You're missing some details!\nRemember to include the description "
                        + "followed by '/at [INSERT DATETIME]'");
            }
            response = tl.createEvent(taskDesc);
            break;
        case "DELETE":
            if (inputArray.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            taskNum = Integer.parseInt(inputArray[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            response = tl.deleteTask(taskNum);
            break;
        case "BYE":
            tl.closeTaskList();
            break;
        case "FIND":
            if (inputArray.length < 2) {
                throw new DukeException("Please enter a keyword to search!");
            }
            String keyword = inputArray[1];
            response = tl.findTask(keyword);
            break;
        default:
            response = "I'm sorry, but I don't know what that means";
            break;
        }
        return response;
    }
}
