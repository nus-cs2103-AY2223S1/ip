package DukeBot;

import DukeBot.command.Command;

public class Duke {

    private static Storage storage;
    private static TaskList tasks = new TaskList();
    private Ui ui;

    public void run() {
        this.ui = new Ui();
        storage = new Storage("src/main/tasks.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
            System.out.println("Creating new file.");
            tasks = new TaskList();
        }
        ui.showWelcome();
        boolean isExit = false;
        Parser p = new Parser(tasks);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = p.parse(fullCommand);
                c.execute(ui);
                isExit = c.isExit();
                ui.showLine();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        storage.write(tasks);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
