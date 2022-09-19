package kkbot;

import kkbot.commands.Command;
import kkbot.exceptions.KKBotException;
import kkbot.parser.Parser;
import kkbot.storage.Storage;
import kkbot.tasklist.TaskList;
import kkbot.ui.Ui;

/**
 * kkbot.kkbot is a chatbot that helps keep track of your to-do list!
 *
 * @author AkkFiros
 */
public class KKBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String loadError;

    /**
     * Constructor for KKBot
     */
    public KKBot() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
            loadError ="";
        } catch (KKBotException e) {
            tasks = new TaskList();
            loadError = e.getMessage();
        }
    }

    /**
     * Returns the load error when retrieving tasks
     * @return the laod error
     */
    public String showLoadError() {
        return loadError;
    }

    /**
     * Method to show welcome message to user
     * @return the welcome message
     */
    public String greet() {
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.initialParse(input);
            return command.execute(tasks, ui, storage);
        } catch (KKBotException e) {
            return e.getMessage();
        }
    }
}
