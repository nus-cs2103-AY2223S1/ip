package duke.main;
import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    protected boolean isExit = false;
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/dude.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(storage);
        }
    }

    public String readCommand(String command) {
        return getResponse(command);
    }

    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.reply;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public String greet() {
        return ui.showWelcome();
    }

//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Duke().run();
//    }

//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    protected String getResponse(String input) {
//        return "Duke: " + input;
//    }

}