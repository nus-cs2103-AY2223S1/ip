import java.io.*;
//import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
//import java.nio.file.Files;
//import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.printErrorMessage(e.toString());
        }
    }

    public void run() {
        Parser.readLine(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
