package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        taskList = storage.load();
        ui = new Ui();
    }

    public void run() {
        ui.showWelcomeMessage();
        ui.readUserInputThenOutputMessage(taskList, storage);
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}
