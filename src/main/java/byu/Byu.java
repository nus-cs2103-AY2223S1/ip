package byu;

import commands.Command;
import exceptions.DukeException;
import java.io.IOException;

public class Byu {

    private FileReader storage;
    private ToDoList tasks;
    private Ui ui;

    public Byu(String filePath) {
        try {
            ui = new Ui();
            storage = new FileReader(filePath);
            tasks = storage.load();
        } catch (IOException e) {
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Byu("./data/Duke.txt").run();
    }

}
