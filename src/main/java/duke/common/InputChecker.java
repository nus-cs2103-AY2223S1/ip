package duke.common;

import duke.DukeException;
import duke.ui.BotUI;

/**
 * Responsible in the initial checks of Adding tasks' input
 * is in the correct format.
 *
 */
public class InputChecker {
    private static final int DETAIL_DATE_SPLITTER_LENGTH = 3;
    private static final int TODO_MINIMUM_SPLIT_SECTION = 2;
    private static final BotUI UI = new BotUI();

    //check whether input can be split to three sections ([detail], [detailDateSplitter] and [dateTime information])
    private static void checkDeadlineAndEvent(String trimmedInput, String command,
                                              String detailDateSplitter) throws DukeException {

        String description = trimmedInput.replace(command, "").trim();
        boolean isSplittable = description.contains(detailDateSplitter);
        if (!isSplittable) {
            throw new DukeException(UI.invalidFormat());
        }

        boolean hasDetail = !description.substring(0, description.indexOf(detailDateSplitter)).isEmpty();
        int splitterIdx = description.indexOf(detailDateSplitter);
        boolean hasDateTimeInfo = !(description.substring(splitterIdx).length() == DETAIL_DATE_SPLITTER_LENGTH);
        if (!hasDetail || !hasDateTimeInfo) {
            throw new DukeException(UI.invalidFormat());
        }

    }

    private static void checkTodo(String trimmedInput) throws DukeException {
        boolean isValidFormat = trimmedInput.split(" ").length >= TODO_MINIMUM_SPLIT_SECTION;
        if (!isValidFormat) {
            throw new DukeException(UI.invalidFormat());
        }
    }

    /**
     * Checks the input of command with adding common as follow:
     * ToDo tasks: Checks the details of tasks is exists in the raw user input.
     * Event/Deadline tasks: Checks the details and date are exists in the raw user input with correct format.
     *
     * @param rawInput String of the user rawInput.
     * @throws DukeException - thrown when the format is invalid.
     */
    public static void checkInput(String rawInput) throws DukeException {
        String trimmedInput = rawInput.trim();
        if (trimmedInput.startsWith("todo")) {
            checkTodo(trimmedInput);
        } else if (trimmedInput.startsWith("deadline")) {
            checkDeadlineAndEvent(trimmedInput, "deadline", "/by");
        } else if (trimmedInput.startsWith("event")) {
            checkDeadlineAndEvent(trimmedInput, "event", "/at");
        }
    }
}
