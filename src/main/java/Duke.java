import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        try {
            taskList = this.storage.load();
        } catch (DukeException de) {
            System.out.println("Duke exception!!");
        }
    }

    public void run() throws DukeException, IOException {
        UI.welcome();
        boolean isByeCommand = false;
        while (!isByeCommand) {
            try {
                Scanner sc = new Scanner(System.in);
                String rawCommand = sc.nextLine();
                Command c = Parser.parse(rawCommand);
                c.execute(taskList, storage);
                isByeCommand = c.isByeCommand;
            } catch (DukeException de) {
                System.out.println(de.toString());
            }
        }

    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke d = new Duke("data/duke.txt");
        d.run();
    }
}
