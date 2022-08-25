package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage);
    }

    public void run() {
        ui.initialize();
        Scanner scan = new Scanner(System.in);
        String item = scan.nextLine();
        while (!item.equals("bye")) {
            Parser.parse(item, tasks);
            item = scan.nextLine();
        }
        ui.exit();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}