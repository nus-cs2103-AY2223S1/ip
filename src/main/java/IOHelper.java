import java.util.Scanner;

/**
 * Handles the input and output that comes from the console
 *
 * @author Rexong
 */
public class IOHelper {
    private Scanner scanner;
    private String text;

    /**
     * Logs text passed into the parameter.
     *
     * @param message message to be logged
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * Constructor with scanner initialised with Scanner.
     */
    public IOHelper() {
        scanner = new Scanner(System.in);
    }

    /**
     * Close the scanner obj.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * read inputs from the console and
     * remove any whitespaces from the front and back.
     */
    public void scan() {
        text = scanner.nextLine();
        text = text.strip();
    }

    /**
     * return the text that has been input into the console.
     *
     * @return text from console
     */
    public String getText() {
        return text;
    }

}
