package duke;

import java.io.FileNotFoundException;

import duke.command.Command;

public class Duke {

    private TaskList list;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            this.list = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isTerminated = false;

        while (!isTerminated) {
            try {
                String next = ui.readCommand();
                ui.showLine();
                Parser input = new Parser(next);
                Command cmd = input.getCommand();
                cmd.execCommand(this.list, this.ui, this.storage);
                isTerminated = cmd.isTerminated();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.home") + "/data/duke.txt").run();
    }
}
