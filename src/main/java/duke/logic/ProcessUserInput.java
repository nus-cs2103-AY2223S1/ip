package duke.logic;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;
import duke.logic.task.ToDo;
import duke.ui.Constants;

import static duke.logic.task.Task.*;

/**
 * Represents methods process user input into command.
 */
public class ProcessUserInput {
    /**
     * Converts user command.
     * @param workList
     */
    public static String process(ArrayList<Task> workList, String userInput) {
        //assert userInput.split("").length == 0 : "User input cannot be empty";
        String typeOfTask = userInput.split(" ")[0];
        switch (typeOfTask) {
        case Constants.LIST:
            return Task.listItems(workList);
        case Constants.UNMARK:
            return markAsNotDone(workList, userInput);
        case Constants.MARK:
            return markAsDone(workList, userInput);
        case Constants.TODO:
            return ToDo.add(workList, userInput);
        case Constants.DEADLINE:
            return Deadline.add(workList, userInput);
        case Constants.EVENT:
            return Event.add(workList, userInput);
        case Constants.DELETE:
            return Task.delete(workList, userInput);
        case Constants.FIND:
            return Task.find(workList, userInput);
            // Fallthrough
        default:
            return new DukeException.InvalidInputException().throwDukeException();
        }
    }
}
