package catbot;

import catbot.command.Command;
import catbot.command.Parser;
import catbot.exception.CatBotException;


/**
 * Creates a chatbot named CatBot.
 */
public class CatBot {

    /** TaskList to handle all tasks related operations. */
    private TaskList taskList;

    /** Storage to handle file reading and writing. */
    private Storage storage;

    /** Ui that handles all interaction with the user. */
    private Ui ui;

    /**
     * Constructor for a CatBot chatbot.
     *
     * @param filePath The file path to read the file from.
     */
    public CatBot(String filePath) {
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        this.storage.readFromFile(this.taskList.getList());
        this.ui = new Ui(this.taskList);
    }

    /**
     * Returns the start message of CatBot.
     *
     * @return The start message of CatBot.
     */
    public String startMessage() {
        return ui.startMessage();
    }

    /**
     * Saves the current tasks to the file.
     */
    public void saveTasks() {
        storage.writeToFile(this.taskList.getList());
    }

    /**
     * Returns the exit message of CatBot.
     *
     * @return The exit message of CatBot.
     */
    public String exitMessage() {
        return ui.exitMessage();
    }

    /**
     * Gets the response from CatBot by taking in a input from the user.
     *
     * @param input The input command from the user.
     * @return The response from CatBot to be displayed.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Error in parsing command";
            c.execute(taskList, ui);
            assert !c.response().equals("") : "error in response";
            return c.response();
        } catch (CatBotException e) {
            return e.toString();
        }
    }
}
