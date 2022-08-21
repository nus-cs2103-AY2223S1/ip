package duke;

import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.read();
                Command command = Parser.parseInput(userInput);
                command.execute(storage, taskList, ui);
                isExit = command.canExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
