package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.toString());
            taskList = new TaskList();
        }
    }

    public void run() throws DukeException, IOException {
        ui.showGreetMessage();
        while (true) {
            ui.showLine();
            String input = ui.readCommand();
            Command currCmd = ui.run(input);
            currCmd.execute(ui, taskList);

            if (currCmd instanceof ExitCommand) {
                break;
            }
        }
        storage.writeFile(taskList);
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/duke.txt").run();
    }
}
