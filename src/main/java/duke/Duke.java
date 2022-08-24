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

    /**
     * Starts the instance of Duke.
     */
    public void run() {
        ui.greet();
        boolean isDone = false;
        while (!isDone) {
            try {
                String input = ui.parseCommand();
                Command c = Parser.parse(input);
                c.handle(this.storage, this.ui, this.taskList);
                isDone = c.isDone();
            } catch (DukeException e) {
                ui.line();
                System.out.println(e);
                ui.line();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
