package duke;

import duke.command.Command;
import duke.parser.Parser;

public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.taskList = storage.load();
        } catch (DukeException e) {
            ui.printException(e);
        }

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        boolean terminateFlag = false;
        ui.printWelcome();

        while (!terminateFlag) {
            try {
                Command currentCommand = parser.parse(ui.getCommand());
                currentCommand.execute(taskList, ui, storage);
                terminateFlag = currentCommand.isTerminatorGetter();
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }
}
