import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private TaskList taskList;
    private Storage storage;

    public Duke() {
        this.storage = new Storage("./../../../data/tasks.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.displayErrorMessage(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Main method for starting the program.
     * @param args Optional command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Ui.welcomeUser();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            this.taskList.printTaskList();
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
