package byu;

import java.io.IOException;

import commands.Command;
import exceptions.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents a chatbot that helps to organize tasks.
 */
public class Byu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image byu = new Image(this.getClass().getResourceAsStream("/images/panda.jpeg"));

    /**
     * Creates an instance of Byu with data from the file.
     */
    public Byu() {
        try {
            ui = new Ui();
            storage = new Storage(ui);
            tasks = storage.load();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Runs Byu and starts scanning for user input.
     * Stops running when "bye" is entered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Creates a Byu instance and runs Byu.
     */
    public static void main(String[] args) {
        new Byu().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                this.tasks.save();
                return "Aww, see you soon!";
            } else {
                c.execute(this.tasks, this.ui);
                return this.ui.showOutput();
            }
        } catch (DukeException e) {
            return ui.showError(e);
        }
        // return "ok";
        // return "Duke heard: " + input;
    }
}
