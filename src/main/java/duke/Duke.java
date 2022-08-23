package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String dirName, String fileName) {
        this.storage = new Storage(dirName, fileName);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage.initializeDir();
            this.storage.initializeFile();
            this.tasks = new TaskList(storage.readFile());
            this.ui.greet();
        } catch (IOException e) {
            this.tasks = new TaskList();
            this.ui.greet();
            ui.printErrorMessage(e, this.tasks);
            ui.printSuccessfulClear();
            ui.printSpacer();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (this.ui.isActive()) {
            try {
                String s = sc.nextLine();
                this.parser.parseCommand(s, this.tasks, this.ui, this.storage);
            } catch (IOException e) {
                ui.printErrorMessage(e, this.tasks);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }
}
