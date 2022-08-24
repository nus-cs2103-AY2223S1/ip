package duke;

import java.io.IOException;

/**
 * Represents the entire chatbot. Central class that initialises all chatbot variables and runs the chatbot
 */
public class Duke {

    private final Storage fileStorage;
    private final Ui ui;
    private final TaskList tasks;

    /**
     * Initialises Duke chatbot
     * @param filePath file on hard disk that tasks are saved
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        fileStorage = new Storage(filePath);
        tasks = new TaskList(fileStorage.loadTasks());
    }

    /**
     * Initialises necessary variables for chatbot and runs the chatbot
     * @throws IOException
     */
    public void run() throws IOException {
        ui.showWelcome();


        while (true) {
            String command = ui.readCommand();
            ui.showLine();
            Parser parser = new Parser(command, ui);
            if (parser.executeCommand(tasks)) {
                break;
            } else {
                ui.showLine();
                fileStorage.saveTasks(tasks);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("tasks.txt").run();
    }
}
