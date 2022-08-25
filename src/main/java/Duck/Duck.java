package Duck;
import Models.Todo;
import Commands.Commands;
import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Duck {
    private Storage storage;
    private TaskList<Todo> list;

    public static void main(String[] args) {
        Duck d = new Duck();
        d.run();
    }

    /**
     * Primary Constructor for the Duck Class.
     * Initializes Storage and TaskList for use in the entire class
     */
    public Duck() {
        try {
            this.storage = new Storage("data", "duck.txt");
            this.list = new TaskList<Todo>(storage.readFromFile());
        }
        catch (IOException e) {
            System.out.println("Error! File not found!");
        }
    }
    /**
     * Run function for the Duck Bot
     * Runs all the necessary functions from the various classes and,
     * acts as an "entry point" to the bot
     */
    public void run() {
        UI.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean isExitCommand = false;
        while (!isExitCommand) {
            userInput = scanner.nextLine();
            Commands c = Parser.parseText(userInput);
            try {
                c.execute(list, storage);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Items do not exist! Or List is empty!");
            }
            isExitCommand = c.isExit();
        }
        UI.printClosingMessage();
    }

    /**
     * Provides a centralized converter to convert date formats
     * Just replace the format here and all other date formats will follow suite
     * @param date date to be converted into string
     * @return  string converted in the dd/MM/yyyy HHmm format
     */
    public static String dateToStringConverter(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return dateFormatter.format(date);
    }
    /**
     * Provides a centralized converter to convert date formats
     * Just replace the format here and all other date formats will follow suite
     * @param date string to be converted into date
     * @return  date converted in the dd/MM/yyyy HHmm format
     */
    public static Date dateStorageConverter(String date) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return dateFormatter.parse(date);
    }

}
