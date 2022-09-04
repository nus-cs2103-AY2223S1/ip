package justin;

import justin.command.Command;

/**
 * Represents the main class in which
 * the program is run.
 * @author Justin Cheng.
 */
public class JustinBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private MainWindow mw;

    /**
     * Constructor for the Duke class.
     * @param filePath The path of the data file
     *                 in String.
     */
    public JustinBot(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            storage.setMw(mw);
            tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the response of user's input in the chat.
     * @param input The user's message in the chat.
     * @return The String message to be sent by the bot.
     * @throws DukeException
     */
    public String getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

}
