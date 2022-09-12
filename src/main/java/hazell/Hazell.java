package hazell;

import hazell.exceptions.HazellException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Main class of the chatbot.
 */
public class Hazell {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private static final String APP_LOGO = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image hazell = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Create a new instance of the chatbot.
     *
     * @param filePath Path to store chatbot data for persistence
     */
    public Hazell(String filePath) {
        ui = new Ui();
        System.out.println(APP_LOGO);
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            taskList = new TaskList();
            ui.reply("Looks like this is the first time you started me up. "
                    + "I'll be saving your tasks to data/hazell.txt!");
        }
        taskList.setStorage(storage);
    }
    public Hazell() {
        this("data/hazell.txt");
    }

    /**
     * Start the chatbot.
     */
    public void run() {
        ui.reply("Hello, I am Hazell!\nWhat can I do for you?");
        Dispatcher dispatcher = new Dispatcher();
        while (ui.hasNextCommand()) {
            Command command = ui.getNextCommand();
            try {
                dispatcher.handle(command, ui, taskList);
            } catch (HazellException ex) {
                ui.reply(ex.toString());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Hazell("data/hazell.txt").run();
    }
}
