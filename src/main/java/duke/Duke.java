package duke;

import duke.command.Command;
import duke.command.CommandOutputs;
import duke.task.TaskList;


/**
 * The Duke program implements a chatbot that
 * helps the user to keep track of various things.
 * <p>
 * The program can take in tasks and add it to a
 * task list, list it out, delete tasks,
 * mark/unmark tasks and save the tasks.
 *
 * @author fungusta
 * @version 0.1
 * @since 2022-08-23
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final ClientList clientList;

    /**
     * Constructor for class Duke.
     *
     */
    public Duke() {
        this.taskList = new TaskList();
        this.clientList = new ClientList();
        this.storage = new Storage("data", "data/Tasks.txt", "data/Clients.txt");
        storage.startUpPullStorage(taskList, clientList);
    }

    /**
     * Returns String output processed by Duke
     *
     * @param input user input
     * @return String output from execution of commands
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            return c.execute(taskList, storage, clientList);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
