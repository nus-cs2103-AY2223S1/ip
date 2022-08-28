package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.data.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;


/** Contains storage, tasks and ui to help interact with user and execute commands */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Initialises the Storage object with the saved tasks.
     *
     * @throws IOException
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes in user input and execute commands until user exits.
     *
     * @throws IOException
     */
 /*   public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.updateFile(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }*/

    /**
     * Displays response in GUI.
     * @param input is the command input.
     * @return the response for the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
               storage.updateFile(tasks);
            }
            return c.execute(tasks,ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
