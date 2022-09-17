package pony;

import pony.command.Command;
import pony.task.TaskList;

public class Pony {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pony(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskList());
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.printLine();
            Command c = Parser.parseCommand(fullCommand);
            c.execute(tasks, storage, ui);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Pony("data/pony.txt").run();
    }
}