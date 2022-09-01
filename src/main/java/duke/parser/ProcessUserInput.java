package duke.parser;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskOperation;
import duke.task.ToDo;
import duke.ui.Constants;

/**
 * Represents methods process user input into command.
 */
public class ProcessUserInput {
    /**
     * Converts user command.
     * @param workList
     */
    public static String process(ArrayList<Task> workList, String userInput) {

        // Processing
        String typeOfTask = userInput.split(" ")[0];
        int index;
        switch (typeOfTask) {
        case Constants.LIST:
            return duke.task.TaskOperation.listItems(workList);
        case Constants.UNMARK:
            try {
                userInput.substring(8);
                index = Integer.parseInt(userInput.split(" ")[1]);
                return workList.get(index - 1).unmark(workList);
            } catch (StringIndexOutOfBoundsException e) {
                return new DukeException.EmptyMarkingException().throwDukeException();
            } catch (NumberFormatException e) {
                return new DukeException.EmptyMarkingException().throwDukeException();
            } catch (IndexOutOfBoundsException e) {
                return new DukeException.EmptyMarkingException().throwDukeException();
            }
        case Constants.MARK:
            try {
                userInput.substring(6);
                index = Integer.parseInt(userInput.split(" ")[1]);
                return workList.get(index - 1).markAsDone(workList);
            } catch (StringIndexOutOfBoundsException e) {
                return new DukeException.EmptyMarkingException().throwDukeException();
            } catch (NumberFormatException e) {
                return new DukeException.EmptyMarkingException().throwDukeException();
            } catch (IndexOutOfBoundsException e) {
                return new DukeException.EmptyMarkingException().throwDukeException();
            }
        case Constants.TODO:
            try {
                // Error when to-do followed by a blank space
                userInput.substring(6);
                // Error when just to-do
                return TaskOperation.add(new ToDo(userInput.substring(5)), workList);
            } catch (StringIndexOutOfBoundsException e) {
                return new DukeException.EmptyTodoException().throwDukeException();

            }
        case Constants.DEADLINE:
            try {
                // Error when deadline followed by a blank space
                userInput.substring(10);
                // Error when just deadline
                String[] deadline = userInput.substring(9).split(" /by ");
                return TaskOperation.add(new Deadline(deadline[0], deadline[1]), workList);
            } catch (StringIndexOutOfBoundsException e) {
                return new DukeException.EmptyDeadlineException().throwDukeException();

            } catch (ArrayIndexOutOfBoundsException e) {
                return new DukeException.DeadlineWithoutByException().throwDukeException();
            }

        case Constants.EVENT:
            try {
                // Error when event followed by a blank space
                userInput.substring(7);
                // Error when just event
                String[] event = userInput.substring(6).split(" /at ");
                return TaskOperation.add(new Event(event[0], event[1]), workList);
            } catch (StringIndexOutOfBoundsException e) {
                return new DukeException.EmptyEventException().throwDukeException();

            } catch (ArrayIndexOutOfBoundsException e) {
                return new DukeException.EventWithoutAtException().throwDukeException();
            }

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
            return Constants.EXIT_MESSAGE;
        }
    }
}
