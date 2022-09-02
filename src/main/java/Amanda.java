package main.java;

import main.java.amanda.command.Command;
import main.java.amanda.exception.AmandaException;
import main.java.amanda.manager.QueryInterpreter;
import main.java.amanda.manager.StoreManager;
import main.java.amanda.manager.TaskList;
import main.java.amanda.ui.Ui;

import java.util.Scanner;

/**
 * Amanda is an interactive chatbot that keeps track of users tasks and deadlines.
 */
public class Amanda {

    private final StoreManager store;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of the Amanda class.
     * @param filePath Path to the storage file of Amanda.
     */
    public Amanda (String filePath) {
        store = new StoreManager(filePath);
        ui = new Ui();
        tasks = new TaskList();
        store.load(tasks);
    }

    /**
     * Runs Amanda
     */
    public void run() {
        ui.showWelcome(); // print welcome message
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) { // amanda runs until isExit return true
            try {
                String fullCommand = ui.readCommand(sc);
                ui.showLine(); // show the divider line ("_______")
                Command c = QueryInterpreter.interpret(fullCommand);
                c.execute(tasks, ui, store); // executes the command
                isExit = c.isExit(); // check if newest user input is an exit command
            } catch (AmandaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Amanda amanda = new Amanda("data/Amanda.txt");
        amanda.run();
    }
}
