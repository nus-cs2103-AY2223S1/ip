package duke;

import duke.exceptions.DukeException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    public Duke() {

        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.ui, this.taskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}