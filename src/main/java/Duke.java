import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This class represents a chat bot.
 */
public class Duke {
    private static boolean isEnd;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for duke
     * @param filePath absolute file path of the storage file
     */
    public Duke(String filePath) {
        isEnd = false;
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadFromFile(tasks);
        ui = new Ui(tasks, storage);
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        ui.welcome();
        while (!isEnd) {
            try {
                ui.Response(myScanner.nextLine());
            } catch (DukeException e){
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Please input the date in the ISO-8601 format\n" +
                        "For example: 2022-08-20T12:00");
            }
        }
        myScanner.close();
    }

    /**
     * Stops the chat bot.
     */
    public static void stop() {
        isEnd = true;
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\xudao\\Documents\\nus\\year 2\\sem 1\\" +
                "2103t\\git stuff\\individual project\\src\\data\\storage.txt").run();
    }
}
