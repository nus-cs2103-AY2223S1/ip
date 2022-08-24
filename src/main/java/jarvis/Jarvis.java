package jarvis;

import java.util.Scanner;

import jarvis.command.Command;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Jarvis --- Task manager.
 */
public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor.
     */
    public Jarvis() {
        this.storage = new Storage(System.getProperty("user.dir") + "/data/tasks.txt");
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (JarvisException e) {
            this.ui.printMessage("Error loading tasks.");
        }
    }

    /**
     *
     */
    public void run() {
        this.ui.greet();

        Scanner sc = new Scanner(System.in);
        boolean terminated = false;

        while (!terminated) {
            try {
                Command command = Parser.parseUserCommand(sc);
                String keyCommand = command.getKeyCommand();

                if (keyCommand.equals("bye")) {
                    terminated = true;
                }

                this.ui.printMessage(command.execute(tasks, storage));
            } catch (JarvisException e) {
                this.ui.printMessage(e.getMessage());
            }
        }
    }
}
