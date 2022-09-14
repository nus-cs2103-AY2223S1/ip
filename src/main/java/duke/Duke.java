package duke;

import java.util.Scanner;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.TaskList;

/**
 * Main class used to handle inputs
 */
public class Duke {
    private TaskList list;
    private Storage storage;
    private String filePath;

    /**
     * Constructor of the duke.Duke class given a filepath
     * @param filePath path of file to be saved/loaded from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.load());
        this.filePath = filePath;
    }

    /**
     * This method runs the duke program
     */
    public static void run() {
        Ui.welcome();
    }

    public String getResponse(String input) {
        return Parser.parse(input, list, filePath);
    }

    /**
     * Main method to run Duke
     *
     * @param args the args for the main method
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        System.out.println(Ui.welcome());
        String in = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) {
                break;
            } else {
                System.out.println(duke.getResponse(in));
            }
        }
        System.out.println(duke.getResponse(in));
    }
}
