package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Duke, the personal chatbot that helps a person keep track of
 * todos, events, deadlines.
 *
 * @author Derrick Khoo
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke.
     *
     * @param filePath the file location for saving data
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            System.out.println(e);
            taskList = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.greet();
    }

    /**
     * Gets Duke's response to the user's input.
     *
     * @param input the input from the user
     * @return Duke's response to the user's input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.handle(this.storage, this.ui, this.taskList);
        } catch (DukeException e) {
            return "Duke says:\n" + ui.formatMessage(e.toString());
        }
    }
}
