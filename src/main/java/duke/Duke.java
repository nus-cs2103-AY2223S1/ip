package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.ImproperFormatException;

import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    public Duke() {

        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.ui, this.taskList, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.storage.isCreated();
        try {
            duke.storage.load(duke.taskList);
        } catch (FileNotFoundException e) {
            System.out.println("PLEASE RESTART DUKE");
        } catch (ImproperFormatException e) {
            System.out.println("CORRUPTED DATA");
        }
        duke.run();
    }
}