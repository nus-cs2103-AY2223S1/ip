package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.gui.UI;
import duke.task.Task;

/**
 * This class is the main logic unit for Duke
 */
public class Duke {
    /* Defined Constants for output */
    private static final String NO_INDEX_MESSAGE = "Did you forget to input an index behind your command?";
    private static final String UNEXPECTED_TERMINATION_MESSAGE = "Unexpected error in Duke";

    /* Handles task list logic. */
    private TaskList taskList;

    /**
     * Constructor for the Duke Chat bot.
     */
    public Duke() {
        taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTasks();
        Storage.load(tasks);
    }

    /**
     * Placeholder for now.
     *
     * @param in User's input.
     * @return Output by Elp's logic unit to return to the user.
     */
    public String getResponse(String in) {
        // Splits the input to retrieve possible commands.
        try {
            Parser.parseInput(in);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return NO_INDEX_MESSAGE;
        }
        Command command = Parser.getUserCommand();
        String userInstructions = Parser.getUserInstructions();

        // Break out of loop
        if (command == Command.BYE) {
            return UI.goodbye();
        }

        // List out current tasks in the list
        if (command == Command.LIST) {
            return taskList.printTaskList();
        }

        if (command == Command.MARK || command == Command.UNMARK) {
            return taskList.taskMarker(command, userInstructions);
        }

        if (command == Command.FIND) {
            return taskList.findTask(userInstructions);
        }

        if (command == Command.DELETE) {
            try {
                return taskList.deleteTask(userInstructions);
            } catch (IndexOutOfBoundsException e) {
                return UI.printDeleteErrorMessage();
            }
        }

        if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
            try {
                return taskList.addTask(command, userInstructions);
            } catch (DukeException e) {
                return UI.printDukeExceptionMessage(e);
            } catch (IOException e) {
                return e.getMessage();
            }
        }
        return UNEXPECTED_TERMINATION_MESSAGE;
    }
}
