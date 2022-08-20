import java.io.*;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("storage/tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
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
                String fullCommand = scanner.nextLine().strip();
                Command command = Parser.parse(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
            } catch (DukeException de) {
                ui.showError(de.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}