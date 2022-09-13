package Duke;

import Duke.commands.Command;

import java.io.IOException;


public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;


    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.fileToList());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullInput = ui.readInput();
                ui.printDash();
                Command c = Parser.parse(fullInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDash();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}




