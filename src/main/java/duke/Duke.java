package duke;

import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(TaskList taskList, String pathName) throws IOException {
        this.taskList = taskList;
        this.storage = new Storage(pathName, this.taskList);
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.storage);

        this.storage.loadUpData();

    }

    public void runUi() {
        this.ui.run();
    }

    public void parse() {
        this.parser.parse();
    }

    public static void main(String[] args) {
        try {
            Duke dukeBot = new Duke(new TaskList(new ArrayList<>(100)), "taskList.txt");

            dukeBot.runUi();

            dukeBot.parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}