package duke;

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
     * @param ui The <code>Ui</code> given to print any outputs
     * @return A boolean indicating whether the program is to be closed
     * @throws DukeException If there is a Duke-specific error encountered
     * @throws IOException If the input cannot be handled by the parser
     */
    public static boolean parse(String input, TaskList tl, Ui ui) throws DukeException, IOException {
        String[] inputArray = input.split(" ");
        boolean isExitCommand = false;

        switch (inputArray[0].toUpperCase()) {
        case "LIST":
            tl.listTasks(ui);
            break;
        case "MARK":
            if (inputArray.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            int taskNum = Integer.parseInt(inputArray[1]) - 1;
            tl.markTask(taskNum, ui);
            break;
        case "UNMARK":
            if (inputArray.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            taskNum = Integer.parseInt(inputArray[1]) - 1;
            tl.unmarkTask(taskNum, ui);
            break;
        case "TODO":
            if (inputArray.length < 2) {
                throw new DukeException("The description of a duke.Todo cannot be empty");
            }
            String[] taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            tl.createToDo(taskDesc, ui);
            break;
        case "DEADLINE":
            taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            taskDesc = String.join(" ", taskDesc).split("/by ");
            if (taskDesc.length < 2) {
                throw new DukeException("You're missing some details!\nRemember to include the description "
                        + "followed by '/by [INSERT DATETIME]'");
            }
            tl.createDeadline(taskDesc, ui);
            break;
        case "EVENT":
            taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
            taskDesc = String.join(" ", taskDesc).split("/at ");
            if (taskDesc.length < 2) {
                throw new DukeException("You're missing some details!\nRemember to include the description "
                        + "followed by '/at [INSERT DATETIME]'");
            }
            tl.createEvent(taskDesc, ui);
            break;
        case "DELETE":
            if (inputArray.length < 2) {
                throw new DukeException("You did not state a task number!");
            }
            taskNum = Integer.parseInt(inputArray[1]) - 1;
            tl.deleteTask(taskNum, ui);
            break;
        case "BYE":
            isExitCommand = true;
            break;
        default:
            ui.printUnknownCommandMessage();
            break;
        }
        return isExitCommand;
    }
}
