package duke;

import duke.task.TasksController;

/**
 * Duke App main class
 */
public class Duke {
    private final Storage storage = new Storage("data/Duke.dat");
    private final TasksController controller = new TasksController(storage.load());
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();

    private void launch() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.startGreeting();
        while (true) {
            String inputText = ui.inputCommand();
            String response = parser.parse(inputText, controller, storage);
            ui.showSplitLine();
            ui.respond(response);
            if (response.equals("Bye. Hope to see you soon!")) {
                System.exit(0);
            }
            ui.continueChat();
            ui.showSplitLine();
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}
