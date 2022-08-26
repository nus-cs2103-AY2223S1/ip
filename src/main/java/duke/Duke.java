package duke;

import java.io.File;
public class Duke{

    public static final String LINE = "____________________________________________________________";
    public static int count = 0;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private  Parser parser;

    public Duke() {
    };

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        storage.load_task(new File("duke.txt"));
        parser.respond();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
