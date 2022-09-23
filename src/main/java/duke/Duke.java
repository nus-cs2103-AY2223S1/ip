package duke;

import duke.command.Command;
import java.io.FileNotFoundException;

/*
Main class that starts the running of Duke Chat Bot
*/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        }
    }

    /**
     * Generates the response of Dukie and executes the command.
     * @param input User input given.
     * @return The response of Dukie.
     */
    public String getResponse(String input) {

        Command command;
        String response;

        try {
            command = Parser.parseUserInput(input);
            response = command.exec(this.tasks, this.storage, this.ui);
        } catch (DukeException e) {
            return e.getMessage();
        }

        return response;
    }

    public String welcome() {
        return "hi I'm Dukie! \nstart by adding a task using one of these commands: \"todo\", \"deadline\", \"event\"";
    }

}