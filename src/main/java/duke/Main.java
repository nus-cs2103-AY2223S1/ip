package duke;

/**
 * Represents the main function that runs the duke object.
 */
public class Main {
    /**
     * Runs the duke object.
     *
     * @param args
     */
    public static void main(String[] args) {
        String fileName = "duke.txt";
        Duke duke = new Duke(fileName);
        duke.run();
    }
}
