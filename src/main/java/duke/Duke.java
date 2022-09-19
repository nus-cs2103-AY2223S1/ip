package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The self-named main class that controls our lovely chatbot, Duke
 *
 * @author eugeneleong
 * @version 1.0
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Loads Duke up with the appropriate list of tasks
     * @throws FileNotFoundException if the filePath is typed incorrectly
     */
    public Duke() throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage("./src/duke.txt");
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) {
        String output;
        try {
            String commandType = Parser.getCommandType(fullCommand);
            output = Parser.process(fullCommand, commandType, tasks, ui);
            // no need to save when saying bye to Duke or listing
            if (!fullCommand.equals("bye") && !fullCommand.equals("list")) {
                storage.saveToFile(fullCommand);
            }
        } catch (IllegalArgumentException iae) {
            return "☹ I'm sorry, but I don't know what that means :-(\n"
                    + "Please key in something I understand. Thank you!";
        } catch (IOException e) {
            return "Your input cannot be saved. Sorry! :(";
        }
        return output;
    }
}

