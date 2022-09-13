package hazell;

import hazell.entities.TaskList;
import hazell.exceptions.HazellException;
import hazell.ui.Cli;
import hazell.ui.UiInterface;

import java.io.IOException;

/**
 * Main class of the chatbot.
 */
public class Hazell {
    private Storage storage;
    private TaskList taskList;
    private Dispatcher dispatcher;
    private UiInterface ui;

    private static final String APP_LOGO = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";

    /**
     * Creates a new instance of the chatbot.
     *
     * @param filePath Path to store chatbot data for persistence
     */
    public Hazell(String filePath) {
        ui = new Cli();
        System.out.println(APP_LOGO);
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            taskList = new TaskList();
            ui.displayBotResponse("Looks like this is the first time you started me up. "
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
        ui.displayBotResponse("Hello, I am Hazell!\nWhat can I do for you?");
        while (ui.hasNextUserInput()) {
            String input = ui.getNextUserInput();
            String response = getResponse(input);
            ui.displayBotResponse(response);
            if (input.equals("bye")) {
                System.exit(0);
            }
        }
    }

    /**
     * Returns the response that the bot should reply, given a user input.
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
