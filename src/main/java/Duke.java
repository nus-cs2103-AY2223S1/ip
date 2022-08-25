/**
 * Creates a chatbot named Duke.
 */
public class Duke {

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

    /** TaskList to handle all tasks related operations. */
    private TaskList taskList;

    /** Storage to handle file reading and writing. */
    private Storage storage;

    /** Ui that handles all interaction with the user. */
    private Ui ui;

    /**
     * Constructor for a Duke chatbot.
     *
     * @param filePath The file path to read the file from.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.storage.readFromFile(this.taskList.getList());
        this.ui = new Ui(this.taskList);
    }

    /**
     * Starts and runs the Duke chatbot program.
     */
    private void run() {
        ui.startMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.newLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                if (!isExit) {
                    ui.newLine();
                }
            }
        }
        ui.exitMessage();
        storage.writeToFile(this.taskList.getList());
    }

}
