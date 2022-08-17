import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.loadLocalData());
        } catch (IOException e) {
            this.taskList = new TaskList();
            System.out.println(e.getMessage());
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
            } catch (DukeException | IndexOutOfBoundsException e) {
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
