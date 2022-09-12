package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.Ui;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/Duke/SavedTask.txt");
        tasks = new TaskList(storage.load());
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
    }




    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(input);
                String output = c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    Platform.exit();
                    return output;
                }
                return output;
            } catch (DukeException e) {
                String output = ui.showLoadingError(e.getMessage());
                return output;
            }
        }
        return "";
    }


    public static void main(String[] args) {
        new Duke("src/main/java/Duke/SavedTask.txt").run();
    }
}
