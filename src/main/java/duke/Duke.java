
package duke;
import duke.command.Command;


/**
 * Main class for the chatbot programme
 */
public class Duke {
    static Ui ui = new Ui();
    static Storage storage = new Storage();
    static TaskList taskList = new TaskList(storage.readFile("duke.txt"));


    public static void main(String[] args) {
        Duke.run();
    }

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

