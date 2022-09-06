package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Duke program implements an application that
 * allows users to input tasks, mark tasks as done/undone,
 * and delete tasks.
 *
 * @author Ying Ting Tan
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor. Initialises Ui, Parser, Storage and TaskList.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Loads task list from storage and passes user input into parser.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.start();
        try {
            storage.read(taskList);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                parser.parseInput(input, ui, storage, taskList);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Creates new Duke object and runs program.
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }



}
