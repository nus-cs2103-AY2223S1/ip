package duke;

import java.io.*;
import java.util.*;

public class Duke {
    private TaskList tasks;

    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }


    public void run() throws IOException {
        Ui.sayGreeting();
        Scanner sc = new Scanner(System.in);
        boolean canQuit = false;

        while (!canQuit) {
            canQuit = Parser.parse(sc.nextLine(), tasks);
        }
    }


    /**
     * The main function.
     */
    public static void main(String[] args) throws IOException {
        new Duke("./src/main/java/duke.txt").run();
    }
}
