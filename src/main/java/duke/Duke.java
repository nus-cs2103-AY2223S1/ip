package duke;

import java.io.File;
public class Duke {
    public static final String line = "____________________________________________________________";
    public static int count = 0;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private  Parser parser;

    public Duke() {
    };

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser();
    }

    public void run() {
        storage.loads(new File("duke.txt"));
        parser.respond();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
