package duke;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Personal Assistant Chatbot that helps a person keep track of several things like tasks.
 *
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.storage.load(this.taskList);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void run() {
        this.ui.printStart();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            String command = this.ui.getCommand(sc);
            exit = this.parser.parse(command, this.ui, this.taskList, this.storage);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();

    }
}
