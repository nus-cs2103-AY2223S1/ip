package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showDukeException(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.inputCommand(fullCommand, tasks, ui);
                c.execute();
                storage.saveDuke(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeException(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.finalGoodbye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
