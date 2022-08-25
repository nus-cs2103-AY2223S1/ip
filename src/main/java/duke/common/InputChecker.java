package duke.common;

import duke.DukeException;
import duke.ui.BotUI;

public class InputChecker {

    private static final BotUI UI = new BotUI();

    private static void checkDeadlineAndEvent(String input, String phrase, String taskSplitter) throws DukeException {
        String description = input.replace(phrase, "").replace(" ", "");
        if (!description.contains(taskSplitter) || description.substring(0, description.indexOf(taskSplitter)).isEmpty()
                || description.substring(description.indexOf(taskSplitter)).length() == 3) {
            throw new DukeException(UI.invalidFormat());
        }
    }
    public static void checkInput(String input) throws DukeException {
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
