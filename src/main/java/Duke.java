package duke;

import java.io.IOException;


/**
 * Represents a ChatBot for managing tasks of 3 types: deadline, todo and event.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean hasReturnError;

    /**
     * Create ChatBot.
     * @param filePath
     * @throws Exception
     */
    public Duke(String filePath) throws Exception {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.readFile());
        this.parser = new Parser();
        this.hasReturnError = false;
    }

    /**
     * Start the ChatBot by greeting the users.
     * @return String : the greetings from Duke.
     */
    public String start() {
        return ui.greet();
    }

    /**
     * Run the ChatBot.
     * Process the command received by the user, implement it and return the respective response.
     * @return String : the respective response by Duke.
     * @throws IOException.
     */
    public String getResponse(String fullCommand) throws IOException {
        String response = "";
        String [] strArr = fullCommand.split(" ");

        try {
            Command c = parser.parse(fullCommand);
            response = c.execute(tasks, ui);
            this.hasReturnError = false;

        } catch (MismatchInputException e) {
            response = ":( OOPS!!! I'm sorry, but I don't know what that means";
            this.hasReturnError = true;

        } catch (TaskWithNoDescriptionException e) {
            response = ":( OOPS!!! The description of a "
                    + strArr[0] + " cannot be empty.";
            this.hasReturnError = true;
        }

        storage.saveNewChanges(tasks);
        return response;
    }

    public boolean getHasReturnError() {
        return this.hasReturnError;
    }

}

