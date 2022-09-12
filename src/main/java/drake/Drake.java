package drake;

import java.io.IOException;
import java.util.List;

import drake.commands.Command;

/**
 * Entrypoint for the Drake to-do list chatbot.
 */
public class Drake {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    private Drake() throws IOException, DrakeException {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.fileToList());
    }

    private void run() {
        ui.replyWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullInput = ui.readInput();
                ui.printDash(); // show the divider line ("_______")
                Command c = Parser.parse(fullInput);
                List<String> reply = c.execute(tasks, ui, storage);
                for (String line : reply) {
                    ui.printLine(line);
                }
                isExit = c.isExit();
            } catch (IOException | DrakeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDash();
            }
        }
    }

    /**
     * Entrypoint for the command-line app.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            new Drake().run();
        } catch (IOException | DrakeException e) {
            System.out.println(e.getMessage());
        }
    }

}
