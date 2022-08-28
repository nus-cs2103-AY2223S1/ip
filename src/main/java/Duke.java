import java.util.Scanner;

/**
 * The main class for the Duke program.
 *
 * @author Samuel Cheong
 * @version 0.1
 */
public class Duke {

    private static TaskList taskArr = new TaskList();
//    private Storage storage;
    private Ui ui;
    String input;

    /**
     * Creates a Duke object using the filePath inputted by the user for storage of tasks.
     *
     * @param filePath The file path to a storage file used for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.printGreetings();

        try {
            Storage.loadFileData(taskArr);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
    }

    /**
     * run is the method that executes the Duke program
     */
    public void run() {
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input,taskArr);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = ui.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
