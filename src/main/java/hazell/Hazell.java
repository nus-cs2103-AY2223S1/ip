package hazell;

import hazell.entities.TaskList;
import hazell.exceptions.HazellException;
import hazell.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of the chatbot.
 */
public class Hazell {
    private Storage storage;
    private TaskList taskList;
    private Dispatcher dispatcher;
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
     * Creates a new instance of the chatbot.
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
        dispatcher = new Dispatcher();
    }
    public Hazell() {
        this("data/hazell.txt");
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        ui.reply("Hello, I am Hazell!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().strip();
            String response = getResponse(input);
            ui.reply(response);
            if (input.equals("bye")) {
                System.exit(0);
            }
        }
    }

    /**
     * Given a user input, returns the response that the bot should reply.
     *
     * @param input User input
     * @return Bot response
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = Command.parse(input);
            response = dispatcher.handle(command, taskList);
        } catch (HazellException ex) {
            response = ex.toString();
        }
        assert !response.equals("") : "Response should not be an empty string";
        return response;
    }

    public static void main(String[] args) {
        new Hazell("data/hazell.txt").run();
    }
}
