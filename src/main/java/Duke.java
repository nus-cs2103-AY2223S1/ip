import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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