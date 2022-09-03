package duke;

/**
 *
 */
public class Duke {
    public static final String FILE_PATH = "tasks.txt";

    private final Ui ui = new Ui();
    private TaskList taskList = null;

    /**
     * The file path at where the tasks are stored
     */

    public Duke() {}

    /**
     * Constructs an instance with the file path
     *
     * @param filePath The path to the file
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        //        try {
        taskList = new TaskList(storage);
        //        } catch (CustomMessageException e) {
        //            ui.showLoadingError();
        //        }
    }

    /**
     * Tne driver method
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        new Duke(Duke.FILE_PATH).run();
    }

    /**
     * The initialisation code that starts the chatbot
     */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(taskList);
        while (true) {
            try {
                String nextCommand = ui.getNextCommand();
                ui.showLine();
                System.out.print(parser.parseUserCommand(nextCommand, parser.breakLoopIndicator));
                if (parser.breakLoopIndicator.getIsExitCommand()) {
                    ui.showExitMessage();
                    break;
                }
            //                Command c = Parser.parse(fullCommand);
            //                c.execute(tasks, ui, storage);
            } catch (CustomMessageException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    // made this public so that it can be used in MainWindow
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
