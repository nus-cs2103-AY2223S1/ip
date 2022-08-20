package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

public class Duke {
    private TaskList listOfTasks;
    private FileStorage taskStorage;
    private Ui ui;
    private Parser parser;

    private Duke(String home) {
        ui = new Ui();
        taskStorage = new FileStorage(home);
        parser = new Parser();
        try {
            initializeDataFile();
            listOfTasks = new TaskList(taskStorage.retrieveFileContents());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            listOfTasks = new TaskList();
        }
    }
    private void run() {
        ui.printIntro();
        while (ui.isScannerActive()) {
            String input = ui.retrieveUserInput();
            try {
                Command command = parser.parse(input);
                command.execute(listOfTasks, taskStorage, ui);
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    private void initializeDataFile() {
        if (!taskStorage.isDirectoryPresent()) {
            taskStorage.createDirectory();
        }
        if (!taskStorage.isFilePresent()) {
            taskStorage.createFile();
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.home")).run();
    }
}
