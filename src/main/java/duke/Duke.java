package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.list.TaskList;
import duke.parser.Parser;
import duke.storage.ListLoader;
import duke.ui.Ui;

/**
 * Represents an interactive 'ToDo list' with set commands to add, modify, and remove tasks.
 *
 * @author WR3nd3
 */
public class Duke {

    /** duke.storage.ListLoader used to load and save remaining tasks  */
    private ListLoader storage;

    /** duke.list.TaskList storing current tasks */
    private TaskList tasks;

    /** duke.ui.Ui handling interactions of input and output with users */
    private final Ui ui;
    private String startUpMessage;

    /**
     * Constructs duke.Duke object and its components.
     */
    public Duke() {
        ui = new Ui();
        startUpMessage = ui.showWelcome();
        tasks = new TaskList();
        storage = new ListLoader(tasks);
        try {
            storage.load();
        } catch (DukeException e) {
            startUpMessage += ui.showLoadingError();
            tasks = new TaskList();
            storage = new ListLoader(tasks);
            storage.load();
        }
    }

    /**
     * Executes the running of the duke.Duke program.
     */
    public void run() {
        ui.showLine();
        System.out.println(startUpMessage);
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            String response = "";
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                response = c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                response = ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println(response);
                ui.showLine();
            }
        }
    }

    /**
     * Provides entry point into duke.Duke program when run through the CLI terminal.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        new Duke().run();
    }



    /**
     * Returns appropriate response to the given input through the form of a string representation
     * of the state and behaviour of the duke program.
     *
     * @param input The input entered by users.
     * @return String representing the appropriate response to the input.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input.trim());
            response = c.execute(tasks, ui, storage);
            return response;
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
            return response;
        }
    }

}


