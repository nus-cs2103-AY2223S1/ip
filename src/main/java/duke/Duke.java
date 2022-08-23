package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke() {
        storage = new Storage("data", "duke.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.retrieveFile());
        } catch (DukeException e) {
            tasks = new TaskList();
            Ui.prettyPrint(e.getMessage());
        }
    }

    public static void run() {
        Ui.greet(tasks.numberOfTasks());
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readUserInput();
                Command cmd = Parser.parse(userInput);
                cmd.execute(tasks, storage);
                isRunning = cmd.isStillRunning();
            } catch (DukeException e) {
                Ui.prettyPrint(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
