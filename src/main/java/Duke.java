import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(this.storage.loadLocalData());
        } catch (IOException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        this.ui.printGreeting();
        while (!isExit) {
            try {
                String userInput = this.ui.read();
                Command command = Parser.parseInput(userInput);
                command.execute(this.storage, this.taskList, this.ui);
                isExit = command.getExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e.getMessage());
            } catch (DateTimeParseException e) {
                this.ui.printErrorMessage("Please format date in YYYY-MM-DD.");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
