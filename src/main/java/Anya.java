package anya;

import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * Represents a ChatBot for managing tasks of 3 types: deadline, todo and event.
 */

public class Anya {

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
    public Anya(String filePath) throws Exception {
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
     * @throws AnyaException.
     */
    public String getResponse(String fullCommand) throws IOException, AnyaException {
        String response = "";
        String [] strArr = fullCommand.split(" ");

        try {
            Command c = parser.parse(fullCommand);
            response = c.execute(tasks, ui);
            this.hasReturnError = false;

        } catch (AnyaException e) {
            response = e.getMessage();
            this.hasReturnError = true;
        } catch (DateTimeParseException e) {
            response = ui.printDateFormatError();
            this.hasReturnError = true;
        } catch (NumberFormatException e) {
            response = ui.printWrongTypeInputException();
            this.hasReturnError = true;
        }

        storage.saveNewChanges(tasks);
        return response;
    }

    public boolean getHasReturnError() {
        return this.hasReturnError;
    }

}

