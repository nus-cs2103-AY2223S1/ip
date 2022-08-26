import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;


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