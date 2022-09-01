package seedu.duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {

    private static String DATA_FILE_PATH = "./Duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialise a new Duke instance with the specified filePath
     *
     * @param filePath the file path for the storage file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser(this.tasks, this.ui, this.storage);
    }

    public void run() {
        try {
            File f = new File(DATA_FILE_PATH);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        this.storage.loadFromFile(tasks);

        String text = "";
        Scanner reader = new Scanner(System.in);
        this.ui.start();

        while (this.ui.isActive()) {
            try {
                text = reader.nextLine();
                this.parser.parse(text);
            } catch (Parser.DukeException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}
