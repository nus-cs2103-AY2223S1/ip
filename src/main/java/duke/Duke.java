package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the duke application
 */
public class Duke {
    /** object to store and manage the tasks*/
    private Storage storage;

    /** Object to handle the UI of the application */
    private Ui ui;

    /** Object that represent the list of tasks */
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage);
    }

    /**
     * Runs the main application of duke
     */
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