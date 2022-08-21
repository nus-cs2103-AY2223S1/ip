package roofus;

import roofus.command.Command;

import java.io.FileNotFoundException;

public class Roofus {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public Roofus(String storagePath) {
        this.storage = new Storage(storagePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (FileNotFoundException err) {
            ui.errMessage("Required file not found\nRoofus did not load storage data");
        }
    }

    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, storage, ui);
                isRunning = c.isRunning();
            } catch (RoofusException err) {
                ui.errMessage(err.getMessage());
            }
            if (!isRunning) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        new Roofus( System.getProperty("user.home") +
                "/data/roofus.txt").run();
    }
}
