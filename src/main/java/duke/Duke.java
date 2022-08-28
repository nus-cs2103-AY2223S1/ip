package duke;

import java.util.Scanner;

import exceptions.DukeException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

/**
 * A basic CLI application for managing tasks.
 */
public class Duke {
    /**
     * Enum for task type.
     */
    public enum TaskType {
        EVENT, DEADLINE, TODO
    }

    /**
     * The TaskList instance which manages the user's tasks.
     */
    private TaskList taskList;

    /**
     * The Storage instance which allows for the caching of
     * a user's tasks between sessions.
     */
    private Storage storage;

    /**
     * Constructor for a Duke instance.
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.displayErrorMessage(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the application.
     * @param args Optional command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Parses the user's input and decides the appropriate method to
     * call based on the internal decision tree implemented in Parser.
     *
     * Terminates if the user enters 'bye'.
     */
    public void run() {
        Ui.welcomeUser();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            if (s.equals("bye")) {
                Ui.sayGoodbye();
                break;
            } else if (s.equals("list")) {
                this.taskList.listTasks();
            } else {
                try {
                    Parser.decide(s, arr, this.taskList, this.storage);
                } catch (DukeException e) {
                    Ui.displayErrorMessage(e);
                }
            }
        }
    }
}
