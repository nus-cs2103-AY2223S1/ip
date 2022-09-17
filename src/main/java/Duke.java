
import java.util.Scanner;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.UI;
import duke.command.Command;
import duke.Parser;

import duke.command.ResponseCommand;

/**
 * Duke class that contains a task list and storage
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke class
     *
     * @param filePath String
     */


    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = this.storage.load();
        } catch (DukeException de) {
            System.out.println("Duke exception!!");
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void run() throws DukeException {
        UI.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String rawCommand = sc.nextLine();
                Command c = Parser.parse(rawCommand);
                c.execute(taskList, storage);
                isExit = c.isByeCommand();
            } catch (AssertionError ae) {
                UI.response(ae.getMessage());
                Command c = new ResponseCommand(ae.getMessage());
                c.execute(taskList, storage);
            } catch (DukeException de) {
                System.out.println(de);
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        Duke d = new Duke("duke.txt");
        d.run();
    }
}

