package hazell;

import hazell.exceptions.HazellException;
import hazell.exceptions.UnknownCommand;

import java.io.IOException;

/**
 * Main class of the chatbot.
 */
public class Hazell {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String APP_LOGO = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";

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

    public static void main(String[] args) {
        new Hazell("data/hazell.txt").run();
    }
}
