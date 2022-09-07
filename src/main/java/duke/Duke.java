package duke;

import duke.command.Command;
import duke.command.CommandOutputs;


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
    private final CommandOutputs commandOutputs;

    private final ClientList clientList;

    /**
     * Constructor for class Duke.
     *
     */
    public Duke() {
        this.taskList = new TaskList();
        this.commandOutputs = new CommandOutputs();
        this.clientList = new ClientList();
        this.storage = new Storage("data", "data/Duke.txt");
        storage.startUpPullStorage(commandOutputs, taskList, clientList);
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
            return c.execute(taskList, commandOutputs, storage, clientList);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
