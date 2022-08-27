package chatbot;

import java.io.IOException;

import chatbot.commands.Command;
import chatbot.exceptions.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.tasks.*;
import chatbot.ui.UI;

/**
 * The Duke chatbot named Zlimez functions as a todo list
 * that helps users keep track of their upcoming tasks and
 * the completion status of each of them.
 *
 * @author James Chiu
 */
public class Duke {
//    private static final String root = System.getProperty("user.dir");
//    private static final Path dataPath = Paths.get(root, "data", "data.txt");
    private boolean isActive = true;
    private UI ui;
    private Storage storage;
    private TaskList taskList;
    private Command currentCommand;

    public Duke() {
        ui = new UI();
        storage = new Storage();
        taskList = new TaskList();
    }

    public static void main(String[] args) throws IOException {
        Duke bot = new Duke();
        bot.start();
        bot.respond();
        bot.exit();
    }

    public void start() throws IOException {
        ui.greet();
        try {
            storage.initData(taskList);
        } catch (DukeException e) {
            ui.reprimand(e);
        }
    }


    /**
     * The method determines the subsequent action performed by the chatbot
     * upon user input.
     * @throws DukeException The various exceptions that can be raised
     * when the chatbot is used.
     */
    public void respond() {
        do {
            String input = ui.getUserInput();
            try {
                currentCommand = Parser.parse(input);
                currentCommand.execute(taskList, ui);
            } catch (DukeException e) {
                ui.reprimand(e);
            }
        } while (!currentCommand.isExit());
    }

    public void exit() throws IOException {
        storage.saveData(taskList);
    }

    /**
     * The method allows user to add different types of task to their todo list.
     *
     * @param task The String that should contain the information of the given task.
     * @throws DukeException if the input given does not correspond to a valid command.
     * @throws IndexOutOfBoundsException if the input is not correctly formatted .
     */


    /**
     * The method allows user to delete a given task from the todo list.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index to be deleted does not exist.
     */


    /**
     * The method lists out all the tasks in the todo list.
     */


    /**
     * The method allows user to mark a task as completed.
     *
     * @param index The task to be marked as completed.
     * @throws DukeException If no task exists at the provided index.
     */

    /**
     * The method allows user to mark a task as incomplete.
     *
     * @param index The task to be marked as incomplete.
     * @throws DukeException If no task exists at the provided index.
     */
}
