package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;


public class Duke {
    private Ui ui;
    private Parser parser;
    private boolean isEnded;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser(ui);
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
