package pixel;

import java.io.IOException;
import java.util.Scanner;

import pixel.util.InvalidTextDataFormatException;
import pixel.util.Parser;
import pixel.util.Storage;

/**
 * Main class of chatbot
 */
public class Pixel {

    private static int taskCount = 0;
    private final Scanner myScanner = new Scanner(System.in); // Create a Scanner object
    private final Parser parser;

    public Pixel(String filePath) {
        this.parser = new Parser(filePath);
    }

    public static void resetTaskCount(int newCount) {
        taskCount = newCount;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void addOneToTaskCount() {
        taskCount += 1;
    }

    public static void minusOneToTaskCount() {
        taskCount -= 1;
    }

    // for other classes to use
    public String parserParse(String userInput) {
        return this.parser.parse(userInput);
    }

    // obsolete since the implementation of GUI
    public void run() {
        while (myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine(); // Read user input
            parser.parse(userInput);
            run();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}

