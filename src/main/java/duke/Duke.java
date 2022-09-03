package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Main Class for Duke
 */
public class Duke {

    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath - the name of the file to load from
     */
    public Duke(String filePath) {
        tasks = new TaskList(Storage.load(filePath));
        parser = new Parser();
    }

    /**
     * Empty Constructor for the Duke class for JavaFX.
     */
    public Duke(){
        tasks = new TaskList();
        parser = new Parser();
    };

    /**
     * Runs the main Duke program.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.setup();
        while (true) {
            try {
                String line = scanner.nextLine();
                System.out.println(Ui.divider());

                // Returns 0 if line has parsed without issue, 1 if program has ended
                int result = this.parser.parse(line, tasks);

                if (result == 1) {
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String response = this.parser.execute(input, tasks);
        return response;
    }

    public static void main(String[] args) {
         new Duke("./data/duke.txt").run();
    }
}
