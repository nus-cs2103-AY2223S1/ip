package duke;

import java.io.IOException;

/**
 * Contains the methods and attributes necessary for Duke.
 */
public class Duke {

    private static TaskList tasks;
    private static Storage storage;
    private Ui ui;

    /**
     * Default public constructor which stores the saved list in /data/duke.txt.
     */
    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Creates new TaskList, Storage and Ui for Duke to run.
     * @param filePath the path of the file the user wishes to store and write data to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Handles and responds to an input submitted by a user. If the input is unclear,
     * the function will display the error details.
     *
     * @param userInput the text inputted by the user
     * @return the reply in response to the input
     */
    public String getResponse(String userInput) {
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
