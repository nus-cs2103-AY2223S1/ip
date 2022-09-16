package yuna;

import yuna.command.Command;
import yuna.exception.YunaException;
import yuna.parser.Parser;
import yuna.storage.Storage;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * Yuna, a chatbot that helps you keep track of the tasks you have.
 *
 * @author Bryan Ng Zi Hao
 */
public class Yuna {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Yuna.
     *
     * @param filePath The location where the data is stored for Yuna.
     */
    public Yuna(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
        } catch (YunaException e) {
            ui.formatMessage("Unable to load file.");
            taskList = new TaskList();
        }
    }

    /**
     * The message shown when Yuna starts up.
     *
     * @return The greeting for Yuna.
     */
    public String getGreeting() {
        return Ui.greet();
    }

    /**
     * Executes the command specified.
     *
     * @return the String representation of the reply.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(ui, storage, taskList);
        } catch (YunaException e) {
            return ui.formatMessage(String.valueOf(e));
        }
    }
}
