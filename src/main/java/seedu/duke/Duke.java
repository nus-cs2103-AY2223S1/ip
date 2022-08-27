package seedu.duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {

    private static String dataFilePath = "./Duke.txt";

    private Storage storage;
    private TaskList taskL;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskL = new TaskList();
        this.parser = new Parser(this.taskL, this.ui, this.storage);
    }

    public void run() {
        try {
            File f = new File(dataFilePath);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        this.storage.loadFromFile(taskL);

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
        new Duke(dataFilePath).run();
    }
}
