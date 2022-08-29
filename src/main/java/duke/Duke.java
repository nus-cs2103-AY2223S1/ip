
package duke;
import duke.command.Command;


/**
 * Main class for the chatbot programme
 */
public class Duke {
    static Ui ui = new Ui();
    static Storage storage = new Storage();
    static TaskList taskList = new TaskList(storage.readFile("duke.txt"));


    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

