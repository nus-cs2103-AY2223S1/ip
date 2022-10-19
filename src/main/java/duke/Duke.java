package duke;

import java.io.FileNotFoundException;

import javafx.application.Platform;

import parser.Parser;

import storage.Storage;

import task.TaskList;

import ui.UI;


/**
 * Duke is a Personal ChatBot to help keep track of your Tasks.
 *
 * @author joelwong
 */

public class Duke {

    private final UI ui;
    private TaskList tasks;


    /**
     * Creates a new Duke.
     *
     * @param filePath the file path of stored tasks in the text file.
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new UI();
        Storage storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
            assert !this.tasks.getTaskList().isEmpty() : "Task should not be empty";
        } catch (DukeException e) {
            ui.showNoTask();
            //does nothing but instantiate an object
            this.tasks = new TaskList();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/duke.txt");
    }


    /**
     * Takes in the user input and parses it to get the appropriate response from system.
     *
     * @param input The user input.
     * @return The appropriate system response to the user input.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        try {
            String out = parser.parse(tasks, input);

            if (parser.isBye()) {
                try {
                    return execute(out);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            }
            return out;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String execute(String out) {
        new Thread(() -> {
            try {
                Thread.sleep(4000);
                Platform.exit();
            } catch (Exception e) {
                throw new DukeException(ui.threadException());
            }
        }).start();
        return out;
    }
}








