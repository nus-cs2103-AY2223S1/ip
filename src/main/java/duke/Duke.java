package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    public static final String PATH_TO_DATA_DIRECTORY = "./data/";
    public static final String TASK_LIST_STORAGE_NAME = "duke.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH_TO_DATA_DIRECTORY, TASK_LIST_STORAGE_NAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
        Command.setUi(ui);
        Command.setTaskList(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit && Ui.getInputScanner().hasNextLine()) {
            try {
                String input = Ui.getInputScanner().nextLine();
                Command c = Parser.parseCommand(input);
                c.execute();
                storage.writeTaskListToStorage(taskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }
}
