package duke;

import duke.controllers.DialogBox;
import duke.exceptions.DukeException;
import duke.handlers.DukeCommand;
import duke.models.Parser;
import duke.models.Storage;
import duke.models.TaskList;
import duke.models.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class Duke {
    public static TaskList taskList;
    public Ui ui = new Ui();
    public Parser parser = new Parser();

    public Duke() {
        try {
            taskList = Storage.loadTasksFromDisk();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        try {
            DukeCommand command = parser.parseCommand(userInput);
            String result = command.run(taskList, parser.parseContent(userInput));
            ui.showResponse(result);
            return result;
        } catch (DukeException e) {
            assert e.getMessage() == null : "No Duke Exception message.";
            ui.showError(e.getMessage());
            return e.getMessage();
        }
    }

    public void chat (Scanner sc) {
        ui.greet();

        String userInput = sc.nextLine();

        while (!(userInput.equals("Bye") || userInput.equals("bye"))) {
            try {
                DukeCommand command = parser.parseCommand(userInput);
                String result = command.run(taskList, parser.parseContent(userInput));
                ui.showResponse(result);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            userInput = sc.nextLine();
        }
        try {
            Storage.saveTaskToDisk(taskList);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.exit();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Duke().chat(sc);
    }
}
