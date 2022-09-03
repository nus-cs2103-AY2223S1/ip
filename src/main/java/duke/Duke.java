package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates the overall Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private final PrintStream ORIGINAL_OUT = System.out;
    private final PrintStream ORIGINAL_ERR = System.err;

    private void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        System.setErr(ORIGINAL_ERR);
    }

    /**
     * Constructs a Duke which saves data in the given directory and file name.
     *
     * @param dirName name of directory
     * @param fileName name of file
     * @return the Duke
     */
    public Duke(String dirName, String fileName) {
        this.storage = new Storage(dirName, fileName);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage.initializeDir();
            this.storage.initializeFile();
            this.tasks = new TaskList(storage.readFile());
            this.ui.greet();
        } catch (IOException e) {
            this.tasks = new TaskList();
            this.ui.greet();
            ui.printErrorMessage(e, this.tasks);
            ui.printSuccessfulClear();
        }
    }

    /**
     * Starts the Duke application.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (this.ui.isActive()) {
            try {
                String s = sc.nextLine();
                this.parser.parseCommand(s, this.tasks, this.ui, this.storage);
            } catch (IOException e) {
                ui.printErrorMessage(e, this.tasks);
            }
        }
    }

    public String getResponse(String input) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        try {
            this.parser.parseCommand(input, this.tasks, this.ui, this.storage);
        } catch (IOException e) {
            ui.printErrorMessage(e, this.tasks);
        }
        String response = outContent.toString();

        restoreStreams();
        return response;
    }

    public String getGreeting() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        this.ui.greet();
        String response = outContent.toString();

        restoreStreams();
        return response;
    }

    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }
}


