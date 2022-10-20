package duke;

import duke.commands.Command;
import duke.parser.DukeParser;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private DukeParser parser;
    private Ui ui;

    @Override
    public void init() {
        storage = new Storage();
        taskList = new TaskList(storage);
        parser = new DukeParser();
        ui = new Ui(this);
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage);
    }

    /**
     * Process user {@code input} and returns Duke's response.
     *
     * @param input user command
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            String response = command.execute(taskList);
            storage.syncTasksToFile(taskList);
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
