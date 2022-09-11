package duke;

import duke.command.Command;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private LocalStorage storage;

    /*
    JavaFX
     */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new LocalStorage("./data/saveFile.json");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


    /**
     * Run the Duke app.
     */
    public void init() {
        this.ui.start();
        this.tasks.loadFromLocalStorage(this.storage);
    }

    public String enterStringCommand(String commandStr) {
            Command c = ui.readCommand(commandStr);
            String returnStr = c.exec(this.tasks);
            this.storage.save(this.tasks);
            return returnStr;

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.init();
    }

}
