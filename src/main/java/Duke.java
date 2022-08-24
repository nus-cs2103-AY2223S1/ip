import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private Storage storage = new Storage();
    private UI ui = new UI();
    private Parser parser = new Parser();
    private TaskList taskList;

    public Duke() {
    }

    private void run() {
        ui.showWelcome();
        taskList = new TaskList(storage.read());
        while(true) {
            try {
                Command command = parser.parse(ui.readCommand());
                command.execute(storage,ui,taskList);
            } catch(DukeException e) {
                ui.showError(e);
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}