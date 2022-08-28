package unc;

import unc.command.Command;

public class Unc {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Unc(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (UncException e) {
                //ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
        storage.save(taskList);
    }


    public static void main(String[] args) {
        new Unc("data/tasks.txt").run();
    }
}
