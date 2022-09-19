package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A chatbot that helps to keep track of tasks.
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.ui = new Ui();
        try {
            listOfTasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            System.out.println("Please create a new folder 'data' in the 'iP' folder");
            listOfTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Parser parser = new Parser(listOfTasks, ui, storage);
        if (input.isEmpty()) {
            return ui.invalidCommandErrorString();
        } else if (input.equals("help")) {
            return ui.helpMsg();
        }else if (input.equals("bye")) {
            return ui.endingMsg();
        } else {
            return parser.checkAndExecuteCommand(input);
        }
    }
}
