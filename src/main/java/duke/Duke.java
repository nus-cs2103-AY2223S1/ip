package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;


public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private boolean isEnded;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(this.ui, this.storage);
        this.parser = new Parser(ui, storage, tasks);
        this.isEnded = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        ui.greet();
        while (!this.isEnded) {
            command = sc.nextLine();
            try {
                isEnded = parser.handler(command);
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
