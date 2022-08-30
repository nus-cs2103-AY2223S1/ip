package duke;

import java.util.Scanner;

/**
 * Duke is a personal chatbot to keep track of things.
 *
 * @author Aaron Tan
 */
public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs an instance of Duke.
     */
    Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    private void run() {
        ui.introduction();
        tasks = storage.readData();

        String input = SCANNER.nextLine();
        while (!input.equals("bye")) {
            parser.process(input, tasks);
            input = SCANNER.nextLine();
        }
        storage.saveData(tasks);
    }
    public static void main(String[] args) {
        new Duke().run();
    }




}
