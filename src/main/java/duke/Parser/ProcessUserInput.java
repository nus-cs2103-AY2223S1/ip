package duke.Parser;

import duke.Exception.DukeException;
import duke.Storage.DukeEncoder;
import duke.TaskList.*;
import Ui.Constants;

import java.util.ArrayList;

public class ProcessUserInput {
    /**
     * Converts user command.
     * @param workList
     */
    public static String process(ArrayList<Task> workList, String userInput) {

        // Processing
        while (!userInput.equals(Constants.EXIT)) {
            String typeOfTask = userInput.split(" ")[0];
            int index;
            switch (typeOfTask) {
            case Constants.LIST:
                return duke.TaskList.TaskOperation.listItems(workList);
            case Constants.UNMARK:
                try {
                    userInput.substring(8);
                    index = Integer.parseInt(userInput.split(" ")[1]);
                    return workList.get(index - 1).unmark();
                } catch (StringIndexOutOfBoundsException e) {
                    new DukeException.EmptyMarkingException();
                    break;
                } catch (NumberFormatException e) {
                    new DukeException.EmptyMarkingException();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    new DukeException.EmptyMarkingException();
                    break;
                }
            case Constants.MARK:
                try {
                    userInput.substring(6);
                    index = Integer.parseInt(userInput.split(" ")[1]);
                    return workList.get(index - 1).markAsDone();
                } catch (StringIndexOutOfBoundsException e) {
                    new DukeException.EmptyMarkingException();
                    break;
                } catch (NumberFormatException e) {
                    new DukeException.EmptyMarkingException();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    new DukeException.EmptyMarkingException();
                    break;
                }
            case Constants.TODO:
                try {
                    // Error when to-do followed by a blank space
                    userInput.substring(6);
                    // Error when just to-do
                    return TaskOperation.add(new ToDo(userInput.substring(5)), workList);
                } catch (StringIndexOutOfBoundsException e) {
                    new DukeException.EmptyTodoException();
                    break;
                }
            case Constants.DEADLINE:
                try {
                    // Error when deadline followed by a blank space
                    userInput.substring(10);
                    // Error when just deadline
                    String[] deadline = userInput.substring(9).split(" /by ");
                    return TaskOperation.add(new Deadline(deadline[0], deadline[1]), workList);
                } catch (StringIndexOutOfBoundsException e) {
                    new DukeException.EmptyDeadlineException();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    new DukeException.DeadlineWithoutByException();
                }
                break;
            case Constants.EVENT:
                try {
                    // Error when event followed by a blank space
                    userInput.substring(7);
                    // Error when just event
                    String[] event = userInput.substring(6).split(" /at ");
                    return TaskOperation.add(new Event(event[0], event[1]), workList);
                } catch (StringIndexOutOfBoundsException e) {
                    new DukeException.EmptyEventException();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    new DukeException.EventWithoutAtException();
                }
                break;
            case Constants.DELETE:
                try {
                    userInput.substring(8);
                    index =  Integer.parseInt(userInput.split(" ")[1]);
                    return TaskOperation.delete(workList.get(index-1), workList);
                } catch (StringIndexOutOfBoundsException e) {
                    new DukeException.EmptyDeleteException();
                    break;
                } catch (NumberFormatException e) {
                    new DukeException.EmptyDeleteException();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    new DukeException.EmptyDeleteException();
                    break;
                }
            case Constants.FIND:
                System.out.println(Constants.FIND_MESSAGE);
                String keyword = userInput.substring(5);
                for (int i = 0; i < workList.size(); i++) {
                    if (workList.get(i).contain(keyword)) {
                        System.out.println(workList.get(i).toString());
                    }
                }
                break;
            default:
                new DukeException.InvalidInputException();
                break;
            }
            // Update data
            DukeEncoder.rewriteList(workList);
        }

        // Update data
        DukeEncoder.rewriteList(workList);
        return "Great that you joined. See you soon. Bye!";
    }
}
