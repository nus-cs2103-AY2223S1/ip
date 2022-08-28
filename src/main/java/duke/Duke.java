package duke;

import java.io.IOException;
import java.util.Scanner;
import duke.command.Command;

/**
 * Main class of the Duke program
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        try {
            taskList = this.storage.load();
        } catch (DukeException de) {
            System.out.println("duke.Duke exception!!");
        }
    }

    public void run() {
        UI.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String rawCommand = sc.nextLine();
                Command c = Parser.parse(rawCommand);
                c.execute(taskList, storage);
                isExit = c.isByeCommand();
            } catch (DukeException de) {
                System.out.println(de);
            }
        }

    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke d = new Duke("data/duke.txt");
        d.run();
    }
}
