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
        String workingDir = System.getProperty("user.dir");
        this.storage = new Storage(workingDir + "/out/duke.txt");
        this.ui = new Ui();
        try {
            listOfTasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            System.out.println("Please create a new folder 'data' in the 'iP' folder");
            listOfTasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input input by user.
     * @return response from PAL.
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
