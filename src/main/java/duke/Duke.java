package duke;

import duke.task.TasksController;
import duke.command.Command;

/**
 * Duke App main class
 */
public class Duke {
    private static final Storage storage = new Storage("data/Duke.dat");
    private static final TasksController controller = new TasksController(storage.load());
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();

    private void launch() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.startGreeting();
        while (true) {
            String commandText = ui.inputCommand();
            Command command = parser.parse(commandText, ui);
            command.execute(controller, ui, storage);
            ui.showCommandList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}
