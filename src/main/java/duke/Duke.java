package duke;

import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage);
        this.parser = new Parser(this.taskList);
        this.ui = new Ui();
    }

    private void run() {
        ui.start(parser);
    }

    public static void main(String[] args) {
        new Duke("./data/history.txt").run();
    }
}


