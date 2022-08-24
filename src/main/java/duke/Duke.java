package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A bot which helps to track things.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.startMessage();
        Parser parser = new Parser(this.tasks);
        String input = "";
        Scanner scan = new Scanner(System.in);
        input = scan.nextLine();

        while (parser.parse(input)) {
            input = scan.nextLine();
            storage.writeToFile(tasks);
        }
        Ui.endMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}