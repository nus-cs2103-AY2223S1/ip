package duke.common;

import duke.DukeException;
import duke.ui.BotUI;

/**
 * Responsible in the initial checks of Adding tasks' input
 * is in the correct format.
 *
 */
public class InputChecker {

    private static final BotUI UI = new BotUI();

    private static void checkDeadlineAndEvent(String input, String phrase, String taskSplitter) throws DukeException {
        String description = input.replace(phrase, "").replace(" ", "");
        if (!description.contains(taskSplitter) || description.substring(0, description.indexOf(taskSplitter)).isEmpty()
                || description.substring(description.indexOf(taskSplitter)).length() == 3) {
            throw new DukeException(UI.invalidFormat());
        }
    }

    /**
     * Checks the input of command with adding common as follow:
     * ToDo tasks: Checks the details of tasks is exists in the raw user input.
     * Event/Deadline tasks: Checks the details and date are exists in the raw user input with correct format.
     *
     * @param input command of the user input.
     * @throws DukeException - thrown when the format is invalid.
     */
    public static void checkInput(String input) throws DukeException {
        input = input.trim();
        if (input.startsWith("todo")) {
            if (input.split(" ").length < 2) {
                throw new DukeException(UI.invalidFormat());
            }
        } else if (input.startsWith("deadline")) {
            checkDeadlineAndEvent(input, "deadline", "/by");
        } else if (input.startsWith("event")) {
            checkDeadlineAndEvent(input, "event", "/at");
        }
    }
}
