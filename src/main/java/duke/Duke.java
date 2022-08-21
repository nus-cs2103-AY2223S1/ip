package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String folderPath, String filename) {
        storage = new Storage(folderPath, filename);
        tasks = new TaskList();
        ui = new Ui();
    }

    private void startChatBot() {
        ui.greet();
        try {
            tasks = new TaskList(storage.readSavedTasks());
        } catch (DukeException e) {
            ui.sayErrorMessage(e.getMessage());
        }
        Parser parser = new Parser(tasks, ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                command.run();
                isExit = command.isExit();
            } catch (DukeException e) {
               ui.sayErrorMessage(e.getMessage());
            }
        }
        try {
            storage.writeToFile(tasks);
            ui.sayGoodbye();
        } catch (DukeException e) {
            ui.sayErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke uncleCheong = new Duke("data", "duke.txt");
        uncleCheong.startChatBot();
    }
}
