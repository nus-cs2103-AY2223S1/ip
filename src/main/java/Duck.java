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
    /**
     * main function for running the Duck bot
     * Works by parsing through the user input and splitting it at first by whitespace
     * This allows the bot to read the command and go down the appropriate case
     * It also allows the program to keep track of the arguments the user gives
     * It also handles possible invalid inputs given by the user
     * **/
    public static void main(String[] args) {
        Duck d = new Duck();
        d.run();
    }

    public Duck() {
        try {
            this.storage = new Storage("data", "duck.txt");
            this.list = new TaskList<Todo>(storage.readFromFile());
        }
        catch (IOException e) {
            System.out.println("Error! File not found!");
        }
    }
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

    public static String dateToStringConverter(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return dateFormatter.format(date);
    }
    public static Date dateStorageConverter(String date) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return dateFormatter.parse(date);
    }

}
