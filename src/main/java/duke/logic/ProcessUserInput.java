package duke.logic;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;
import duke.logic.task.TaskOperation;
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
        int index;
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
            try {
                userInput.substring(8);
                index = Integer.parseInt(userInput.split(" ")[1]);
                return TaskOperation.delete(workList.get(index - 1), workList);
            } catch (StringIndexOutOfBoundsException e) {
                return new DukeException.EmptyDeleteException().throwDukeException();

            } catch (NumberFormatException e) {
                return new DukeException.EmptyDeleteException().throwDukeException();

            } catch (IndexOutOfBoundsException e) {
                return new DukeException.EmptyDeleteException().throwDukeException();

            }
        case Constants.FIND:
            System.out.println(Constants.FIND_MESSAGE);
            String keyword = userInput.substring(5);
            for (int i = 0; i < workList.size(); i++) {
                if (workList.get(i).contain(keyword)) {
                    System.out.println(workList.get(i).toString());
                }
            }
            // Fallthrough
        default:
            return new DukeException.InvalidInputException().throwDukeException();
        }
    }
}
