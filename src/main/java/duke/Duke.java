package duke;

/**
 *
 */
public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    /**
     * The file path at where the tasks are stored
     */
    public static String filePath = "tasks.txt";

    /**
     * Constructs an instance with the file path
     * @param filePath The path to the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
//        try {
            taskList = new TaskList(storage);
//        } catch (CustomMessageException e) {
//            ui.showLoadingError();
//        }
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
     * Tne driver method
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        new Duke(Duke.filePath).run();
    }
}
