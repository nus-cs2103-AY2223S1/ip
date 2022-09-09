package duke;

import duke.commands.Command;
import duke.controllers.MainWindow;
import duke.tasks.*;
import duke.ui.Ui;
import duke.utils.InputParser;
import duke.utils.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Duke extends Application {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private InputParser inputParser;
    private static final File SAVE_FILE = new File("savedata.txt");
    private static final URL MAIN_WINDOW_FXML = Duke.class.getResource("/view/MainWindow.fxml");

    @Override
    public void start(Stage stage) throws Exception {
        inputParser = new InputParser();
        ui = new Ui();
        storage = new Storage(SAVE_FILE);
        taskList = new TaskList(storage.loadFromFile());
        loadMainWindow(stage);
    }

    private void loadMainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MAIN_WINDOW_FXML);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        Command cmd = inputParser.parse(input, taskList, storage, ui);
        String response = cmd.execute();
        return response;
    }
}