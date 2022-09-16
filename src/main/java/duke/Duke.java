
package duke;
import duke.command.Command;


/**
 * Main class for the chatbot programme
 */
public class Duke {
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage();
    private static final TaskList taskList = new TaskList(storage.readFile("duke.txt"));



    public String getResponse(String input) {
        try {
            Command c = Parser.parseTask(input);
            return c.execute(taskList, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Launcher.launch(args);
    }
}

