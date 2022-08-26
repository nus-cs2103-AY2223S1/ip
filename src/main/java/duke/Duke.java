package duke;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main code for Duke.
 */
public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Parser p;
    private boolean isExiting;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            list = new TaskList();
        }
        p = new Parser(list);
        isExiting = false;
    }

    public void run() {
        Ui.showHello();
        Scanner sc = new Scanner(System.in);
        do {
            String userInput = sc.nextLine();
            try {
                isExiting = p.parseInput(userInput, false);
            } catch (DukeException e) {
                Ui.showErrorOccurred(e);
            }
        } while (!isExiting);
        sc.close();
        exit();
    }

    public void exit() {
        storage.saveToFile(list);
    }

    public static void main(String[] args) {
        new Duke("data" + File.separator + "taskList.txt").run();
    }
}
