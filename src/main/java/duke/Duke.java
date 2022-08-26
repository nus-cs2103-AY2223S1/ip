package duke;

import duke.command.Command;
import duke.exception.DukeCommandAlreadyExecutedException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 *
 *
 *
 *
 * @param
 * @param
 * @param
 * @param
 * @param
 * @return
 * @throws
 */
public class Duke {

    public static final String TAB = "    ";

    private static final String FILE_PATH = "saved_list.txt";
    private static final String GREETING_MESSAGE = "Hi there! I' am duke.Duke, your personal time manager."
            + "\nWhat can I help you?";

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    Duke(String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    public void run() {
        greet();
        startListening();
    }

    private void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    private void startListening() {

        boolean isExit = false;

        while (!isExit) {
            String input = ui.readInput();

            Command nextCommand = parser.parse(input);
            isExit = nextCommand.isExit();

            try {
                nextCommand.execute(ui, taskList, storage);
            } catch (DukeCommandAlreadyExecutedException exception) {
                ui.printOutput(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke(FILE_PATH);
        chatBot.run();
    }
}
