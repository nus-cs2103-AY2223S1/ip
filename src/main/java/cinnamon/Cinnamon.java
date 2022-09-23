package cinnamon;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import cinnamon.Tasks.Task;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Parser;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;
import cinnamon.command.Command;

/**
 * Duke is the main class that will save and run the program
 *
 * @author Fang Yiye
 */
public class Cinnamon {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor of Duke to initialise ui, storage and scanner
     */
    public Cinnamon() {
        ui = new Ui();
        try {
            storage = new Storage("Data/duke.txt");
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.loadingError();
            taskList = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Executes the command entered by user and returns its response
     *
     * @param input user input
     * @return response by cinnamon
     * @throws DukeException specific exception
     */
    public String getResponse(String input) throws DukeException {
        String response;
        Command command;
        command = Parser.parse(input);
        response = command.execute(taskList, ui, storage);
        return response;
    }


    public String displayBye() {
        storage.writeTasks(taskList);
        return "Bye! Hope to see you again soon!";
    }

    public String displayGreet() {
        return "Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠";
    }

}
