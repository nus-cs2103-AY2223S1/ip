package pixel;

import java.util.Scanner;

import pixel.util.Parser;

/**
 * Main class of chatbot
 */
public class Pixel {

    public static int count = 0; // made public for testing
    private final Scanner myScanner = new Scanner(System.in); // Create a Scanner object
    private final Parser parser;
    private boolean justInitialised = true;

    public Pixel(String filePath) {
        this.parser = new Parser(filePath);
    }

    public boolean botJustInitialised() {
        if (this.justInitialised) {
            this.justInitialised = false;
            return true;
        } else {
            return false;
        }
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

