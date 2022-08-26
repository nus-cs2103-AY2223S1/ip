package duke;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;


public class Duke {

    private final Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Ui.helloMessage();
        Scanner first = new Scanner(System.in);
        boolean canExit = false;
        while(!canExit) {
            canExit = Parser.parse(first.nextLine(), tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/duke.txt").run();
    }
}