package duke;

import duke.command.Command;
import duke.exception.DukeException;
/*
import duke.frontend.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

 */

/**
 * Main file of Duke to be run to initiate the program.
 */
public class Duke {
    //Backend variables
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke instance by default with no properties initialised
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");
        try {
            taskList = new TaskList(storage.load());
            if (taskList.isEmpty()) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Constructs a Duke instance and load the previous stored TaskList from the filePath.
     *
     * @param filePath the path of the .txt file in this project where the TaskList is to be stored.
     * @throws DukeException the parent Exception inherited by self-created exceptions.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            if (taskList.isEmpty()) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initialise the Duke program.
     */
    public void run() {
        //this.ui.showGreetingMessage();
        //this.ui.printList(this.taskList);
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c.isExit();
//            } catch (Exception e) {
//                ui.showError(e.getMessage());
//            }
//        }
        //this.ui.showGoodbyeMessage();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String response = c.execute(taskList, ui, storage);
        return response;
    }
}
