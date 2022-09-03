package duke;

import duke.command.Command;

/** A bot to help you to keep track of your tasks in a to-do list. */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts up the bot.
     * Allows user to interact with the bot and give commands, until the bot is given the command to deactivate.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String nextCommand = ui.readCommand();
                Command c = Parser.parse(nextCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
