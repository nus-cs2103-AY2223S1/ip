package pixel;

import pixel.util.*;

import java.util.Scanner;

/**
 * Main class of chatbot
 */
public class Pixel {

    public static int count = 0; // made public for testing
    private final Scanner myScanner = new Scanner(System.in);  // Create a Scanner object

    // the following fields are made final to facilitate testing, should be private
    // private final String filePath;
    public final Parser parser;

    public Pixel(String filePath) {
        // this.filePath = filePath;
        this.parser = new Parser(filePath);
    }

    private void run() {
        while (myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();  // Read user input
            parser.parse(userInput);
            run();
        }
    }

    public static void main(String[] args) {
        Pixel test = new Pixel("C:/!Education/CS2103/gitFolderOne/data/pixel.txt");
        System.out.println(UserInterface.GREETING_MESSAGE + UserInterface.PROMPT_MESSAGE);
        test.run();
    }
}

