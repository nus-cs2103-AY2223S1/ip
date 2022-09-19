package duke;

import duke.command.Command;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
Main class that starts the running of Duke Chat Bot
*/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ArrayList<Task> list;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
            list = tasks.getTaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            list = tasks.getTaskList();
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
        // String response = dukieUi.showDoNotKnowMessage();

        Command command;
        String response;

        try {
            command = Parser.parseUserInput(input);
            response = command.exec(tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }

        return response;
    }

    public String welcome() {
        return "hi I'm Dukie! \nstart by adding a task using one of these commands: \"todo\", \"deadline\", \"event\"";
    }

}