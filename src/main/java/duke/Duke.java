package duke;

import java.io.FileNotFoundException;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = storage.load();
        } catch (FileNotFoundException err) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /*
    private void run() {
        ui.greet();
        boolean isBye = false;
        while (!isBye) {
            try {
                String commandText = ui.readInput();
                Command command = Parser.parse(commandText);
                command.execute(tasks, ui, storage);
                isBye = command.isByeCommand();
            } catch (DukeException err) {
                System.out.println(err);
            }
        }
    }
     */

    protected String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException err) {
            return err.getMessage();
        } catch (Exception ex) {
            return "Invalid user input ~ hoot";
        }
    }
}
