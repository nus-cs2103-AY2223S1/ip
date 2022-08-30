package pluto;

import pluto.command.Command;

public class Pluto {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pluto(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (PlutoException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PlutoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String path = "PlutoData.txt";
        new Pluto(path).run();
    }
}
