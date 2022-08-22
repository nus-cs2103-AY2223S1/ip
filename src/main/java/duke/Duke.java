package duke;

import duke.exceptions.DukeException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private boolean isEnded;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(Storage.read());
        this.parser = new Parser(ui, storage, tasks);
        this.isEnded = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        Ui.greet();
        while (!this.isEnded) {
            command = sc.nextLine();
            try {
                isEnded = parser.handler(command);
            } catch (DukeException e) {
                Ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
