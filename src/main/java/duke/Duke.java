package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private final UI ui;
    private final Storage storage;
    private final TaskList taskList;

    public Duke() {
        this.ui = new UI();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.initialise());
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, taskList);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printWithDivider(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
