package luffy;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/** Luffy is a todolist program. Built for CS2103T Individual Project 2022 S1.
 * @author Silas Tay A0233425M
 */
public class Luffy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/data.txt";

    public Luffy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.printWelcome();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        //Getting inputs:
        while (true) {
            String nextLine = scanner.nextLine();
            if (nextLine.equals("bye")) {
                break;
            } else {
                parser.parse(nextLine, this.tasks);
            }
            this.storage.updateSaveFile(this.tasks, FILE_PATH);
        }
    }

    public static void main(String[] args) {
        new Luffy(FILE_PATH).run();
    }
}
