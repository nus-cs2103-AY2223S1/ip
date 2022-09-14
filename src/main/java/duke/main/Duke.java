package duke.main;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Duke implements the Duke bot, functions as a simple todo-list.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
 */

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    /**
     * Creates new Duke object.
     *
     * @param filePath the file path to store teh task list
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the greeting message when Duke is opened
     *
     * @return Greeting message.
     */
    public String greeting() {
        return ui.welcomeMessage();
    }

    /**
     * Returns the response from Duke
     *
     * @param input input from the user
     *
     * @return the response from Duke
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
