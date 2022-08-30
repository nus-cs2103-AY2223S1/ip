package myduke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exception.DukeException;

/**
 * This class represents a chat bot.
 */
public class Duke {
    private static boolean isEnd;
    private final Ui ui;

    /**
     * Constructor for duke.
     *
     * @param filePath absolute file path of the storage file.
     */
    public Duke(String filePath) {
        isEnd = false;
        Storage storage = new Storage(filePath);
        TaskList tasks = new TaskList();
        storage.loadFromFile(tasks);
        ui = new Ui(tasks, storage);
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        // Create a Scanner object
        Scanner myScanner = new Scanner(System.in);
        ui.welcome();
        while (!isEnd) {
            try {
                ui.response(myScanner.nextLine());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Please input the date in the ISO-8601 format\n"
                        + "For example: 2022-08-20T12:00");
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

    /**
     * Main.
     * @param args arg
     */
    public static void main(String[] args) {
        //String filepath = "C:\\Users\\xudao\\OneDrive\\Documents\\NUS FILES\\" +
        //        "year 2\\sem 1\\cs2103t\\git\\ip\\src\\data\\storage.txt"
        String filepath = "C:\\Users\\xudao\\Documents\\nus\\year 2\\sem 1\\2103t"
                + "\\git stuff\\individual project\\src\\data\\storage.txt";
        new Duke(filepath).run();
    }
}
