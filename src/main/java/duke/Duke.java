package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.ui.showStorageLoadingMessage();
            this.taskList = storage.load();
            this.ui.showReply(String.format("Save file loaded. You currently have %d tasks.\n", taskList.getLength()));
        } catch (DukeException e) {
            this.ui.showException(e);
            this.taskList = new TaskList();
        } finally {
            this.ui.showSeperator();
        }
    }

    public void exec() {
        boolean exitCalled = false;

        this.ui.showWelcome();
        this.ui.showSeperator();

        while (!exitCalled) {
            try {
                Command runCommand = Parser.parse(this.ui.readCommand());
                runCommand.exec(this.taskList, this.ui, this.storage);
                exitCalled = runCommand.isTerminator();
            } catch (DukeException e) {
                this.ui.showException(e);
            } finally {
                this.ui.showSeperator();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/save.txt").exec();
    }
}
