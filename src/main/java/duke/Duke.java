package duke;

import duke.command.ICommand;
import duke.command.EmptyCommand;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.load());
        this.ui = new Ui();
        ui.showWelcomeMsg();
    }

    public void run() {
        ICommand cmd = new EmptyCommand();
        while (!cmd.isExit()) {
            String input = this.ui.readCommand();
            cmd = Parser.parse(input);
            cmd.execute(storage, taskList, ui);
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke("data/duke.txt");
        dk.run();
    }
}
