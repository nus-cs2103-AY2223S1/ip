package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    
    public Duke(String pathString) {
        this.ui = new Ui();
        this.storage = new Storage(pathString);
        ui.showIsLoading();
        try {
            this.tasks = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch(FileNotFoundException fnfe) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        this.ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                this.ui.showPrompt();
                String fullCommand = scanner.nextLine();
                Command command = Parser.parse(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.showError(de.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke("storage/tasks.txt");
        duke.start();
    }
}