package uwu;

import uwu.command.Command;
import uwu.exception.UwuException;
import uwu.task.TaskList;

public class UwuBot {
    private Storage storage;
    private TaskList tasks;
    private static Ui ui = new Ui();

    public UwuBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (UwuException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showLine();
        ui.greetUsers();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UwuException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new UwuBot("data/taskList.txt").run();
    }
}
