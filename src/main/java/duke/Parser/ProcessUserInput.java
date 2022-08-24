package duke.Parser;

import duke.Exception.DukeException;
import duke.Storage.DukeEncoder;
import duke.TaskList.*;
import Ui.Constants;

import java.util.ArrayList;
import java.util.Scanner;

public class ProcessUserInput {
    public static void process(ArrayList<Task> workList) {
        // User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Processing
        while (!userInput.equals(Constants.EXIT)) {
            String typeOfTask = userInput.split(" ")[0];
            int index;
            switch (typeOfTask) {
                case Constants.LIST:
                    duke.TaskList.TaskOperation.listItems(workList);
                    break;
                case Constants.UNMARK:
                    try {
                        userInput.substring(8);
                        index = Integer.parseInt(userInput.split(" ")[1]);
                        workList.get(index - 1).unmark();
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
                    break;
                case Constants.MARK:
                    try {
                        userInput.substring(6);
                        index = Integer.parseInt(userInput.split(" ")[1]);
                        workList.get(index - 1).markAsDone();
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
                    break;
                case Constants.TODO:
                    try {
                        // Error when to-do followed by a blank space
                        userInput.substring(6);
                        // Error when just to-do
                        TaskOperation.add(new ToDo(userInput.substring(5)), workList);
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyTodoException();
                        break;
                    }
                    break;
                case Constants.DEADLINE:
                    try {
                        // Error when deadline followed by a blank space
                        userInput.substring(10);
                        // Error when just deadline
                        String[] deadline = userInput.substring(9).split(" /by ");
                        TaskOperation.add(new Deadline(deadline[0], deadline[1]), workList);
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
                        TaskOperation.add(new Event(event[0], event[1]), workList);
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
                        TaskOperation.delete(workList.get(index-1), workList);
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
                    break;
                default:
                    new DukeException.InvalidInputException();
                    break;
            }
            // Update data
            DukeEncoder.rewriteList(workList);

            // Next input
            userInput = scanner.nextLine();
        }

        // Update data
        DukeEncoder.rewriteList(workList);
    }
}
