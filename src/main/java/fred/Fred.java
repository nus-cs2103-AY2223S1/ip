package fred;

import commands.Command;

import exception.FredException;

import parser.Parser;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class Fred {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Fred(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FredException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FredException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Fred("data/fred.txt").run();
    }
}
