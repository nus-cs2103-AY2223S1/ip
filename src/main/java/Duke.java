import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/** TODOs
 * update file for mark/unmark
 * update file for delete
 */

public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    protected static boolean terminate = false;

    public Duke(String filePath) {
        storage = new Storage(filePath, "data/temp.txt");
        ui = new Ui();
        tasks = new TaskList(ui, storage.startUp(), storage);
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.greet();
        parser.takeUserInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }










}