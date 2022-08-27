
package duke;
import duke.command.Command;


/**
 * Main class for the chatbot programme
 */
public class Duke {
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage();
    private static final TaskList taskList = new TaskList(storage.readFile("duke.txt"));


    public static void main(String[] args) {
        Duke.run();
    }

    /**
     * Runs the chat-bot Duke.
     */
    public static void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

