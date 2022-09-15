package duke.logic;
import static duke.logic.task.Task.markAsDone;
import static duke.logic.task.Task.markAsNotDone;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;
import duke.logic.task.ToDo;
import duke.ui.TextUi;


/**
 * Represents methods process user input into command.
 */
public class ProcessUserInput {
    /**
     * Converts user command.
     * @param workList
     */
    public static String process(ArrayList<Task> workList, String userInput) {
        assert userInput.split("").length > 0 : "User input cannot be empty";
        String typeOfTask = userInput.split(" ")[0];

        switch (typeOfTask) {
        case TextUi.EXIT:
            return TextUi.EXIT_MESSAGE;
        case TextUi.LIST:
            return Task.listItems(workList);
        case TextUi.UNMARK:
            return markAsNotDone(workList, userInput);
        case TextUi.MARK:
            return markAsDone(workList, userInput);
        case TextUi.TODO:
            return ToDo.add(workList, userInput);
        case TextUi.DEADLINE:
            return Deadline.add(workList, userInput);
        case TextUi.EVENT:
            return Event.add(workList, userInput);
        case TextUi.DELETE:
            return Task.delete(workList, userInput);
        case TextUi.FIND:
            return Task.find(workList, userInput);
            // Fallthrough
        default:
            return new DukeException.InvalidInputException().throwDukeException();
        }
    }
}
