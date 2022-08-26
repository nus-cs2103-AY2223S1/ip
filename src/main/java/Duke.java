import duke.command.Command;
import duke.model.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filePath) {
        Scanner sc = new Scanner(System.in);
        this.ui = new Ui(sc);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }
    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;

        ui.showDivider();
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showDivider();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage, ui);
            isExit = c.getIsExit();
            if (!isExit) {
                ui.showDivider();
            }
        }
        ui.close();
        ui.sayBye();
        ui.showDivider();
    }
}
