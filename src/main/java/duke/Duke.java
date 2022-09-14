package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final String DEFAULT_FILEPATH = "duke.txt";

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.load();
        ui = new Ui();
    }

    public Duke() {
        this(DEFAULT_FILEPATH);
    }

    String getResponse(String input) {
        return ui.readUserInputThenOutputMessage(taskList, storage, input);
    }
}
