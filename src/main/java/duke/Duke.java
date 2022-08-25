package duke;

import duke.commands.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        ui.printWelcome();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.printSuccessfulLoad();
        } catch (DukeException e) {
            ui.printFailedLoad();
            tasks = new TaskList();
        }
    }



    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.toString());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}
