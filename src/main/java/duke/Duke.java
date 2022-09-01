package duke;

import duke.commands.Command;
import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.io.File;
import java.util.Scanner;

/**
 * Runs the CLI Application.
 */
class Duke {
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private final TaskList taskList;
    private final Storage storage;
    private final TextUi textUi;

    public Duke() {
        Storage storage = new Storage(DATA_PATH);
        this.storage = storage;
        this.taskList = new TaskList(storage.load());
        this.textUi = new TextUi();
    }


    /**
     * Initialises the application and begins interacting with the user.
     */
    public void run() {
        textUi.showWelcomeMessage();

        boolean isExit = false;

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        while (!isExit && scanner.hasNextLine()) {
            try {
                String fullCommand = scanner.nextLine();

                Command c = Parser.parse(fullCommand);
                Response response = c.execute(taskList, storage);
                System.out.println(response.getMessage());
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Note: You are strongly encouraged to customize the chatbot name,
     * command/display formats, and even the personality of the chatbot
     * to make your chatbot unique.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
