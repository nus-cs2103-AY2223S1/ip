package duke;

import duke.command.CommandHandler;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * A personal assistant chatbot that helps users keep track of tasks to complete.
 *
 * @author ish1506
 */
public class Duke {
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList(Storage.readData());
    }

    /**
     * Gets a response to the user input.
     *
     * @param input the user input.
     * @return the response message.
     */
    public String getResponse(String input) {
        CommandHandler commandHandler = new CommandHandler(ui, tasks);
        String dukeResponse = commandHandler.execute(input);
        assert !dukeResponse.isEmpty() : "Duke response should not be empty";
        return dukeResponse;
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }
}
