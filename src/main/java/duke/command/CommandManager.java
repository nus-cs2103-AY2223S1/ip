package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A manager class which identifies the command input by the user and process it accordingly.
 */
public class CommandManager {

    /**
     * Handles and responds to an input submitted by a user. If the input is unclear,
     * the function will display the error details.
     *
     * @param tasks the list of tasks currently stored
     * @param userInput the text inputted by the user
     * @param storage the storage object which handles reading and writing of data
     * @return the reply in response to the input
     */
    public String processCommand(TaskList tasks, String userInput, Storage storage) {
        Task newTask = null;
        CommandType type = Parser.parse(userInput);
        String reply = "";

        switch (type) {
        case BYE:
            reply = "Bye. Hope to see you again soon!";
            break;
        case LIST:
            reply = tasks.getList();
            break;
        case MARK:
            try {
                reply = tasks.mark(Integer.valueOf(userInput.substring(5)));
            } catch (DukeException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                return "Please state the index you wish to mark.";
            }
            break;
        case UNMARK:
            try {
                reply = tasks.unmark(Integer.valueOf(userInput.substring(7)));
            } catch (DukeException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                return "Please state the index you wish to unmark.";
            }
            break;
        case TODO:
            newTask = new ToDo();
            break;
        case EVENT:
            newTask = new Event();
            break;
        case DEADLINE:
            newTask = new DeadLine();
            break;
        case DELETE:
            try {
                reply = tasks.delete(Integer.valueOf(userInput.substring(7)));
            } catch (DukeException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                return "Please state the index you wish to delete.";
            }
            break;
        case FIND:
            try {
                reply = tasks.find(userInput.substring(5));
            } catch (DukeException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                return "Please state the keywords you wish to find.";
            }
            break;
        default:
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        if (newTask != null) {
            try {
                newTask.addName(userInput);
                reply = tasks.add(newTask);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        try {
            storage.write(tasks.writeTasks());
        } catch (IOException e) {
            return e.getMessage();
        }

        return reply;
    }
}
