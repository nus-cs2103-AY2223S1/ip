package duke;

import duke.Exceptions.*;

import java.io.File;
import java.util.Scanner;
public class Duke {
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    Duke() {
        Storage storage = new Storage(DATA_PATH);
        this.storage = storage;
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui();
    }

    public void run() {
        this.ui.showWelcomeMessage();

        boolean isExit = false;

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        while (!isExit && scanner.hasNextLine()) {
            try {
                String fullCommand = scanner.nextLine();

                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);

                isExit = c.isExit();

            } catch (InvalidCommandException e) {
                ui.showMessage(e.getMessage());
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
