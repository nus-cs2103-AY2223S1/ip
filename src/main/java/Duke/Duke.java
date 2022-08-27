package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private TaskList tasklist;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String path) {
        tasklist = new TaskList();
        ui = new Ui();
        storage = new Storage(path);
        try {
            storage.readFromFile(tasklist);
        } catch (FileNotFoundException e) {
            ui.fileNotFound();
            tasklist = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Parser.parse(tasklist, ui, storage);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}


