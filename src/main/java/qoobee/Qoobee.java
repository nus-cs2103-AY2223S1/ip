package qoobee;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.Scanner;

/**
 * Contains the main class which is primarily run.
 */
public class Qoobee {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a Qoobee object that contains a ui, storage, parser and tasklist.
     */
    public Qoobee() {
        this.ui = new Ui();
        this.storage = new Storage("TaskList.txt");
        try {
            tasks = new TaskList(storage);
            storage.loadFile();
            parser = new Parser(ui, tasks);
        } catch (QoobeeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(storage);
        }
    }

    /**
     * Starts the bot for the user to interact with.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            parser.parse(input);
            if (!ui.isOn()) {
                break;
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}
