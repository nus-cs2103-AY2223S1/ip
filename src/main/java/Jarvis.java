import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jarvis() {
        this.storage = new Storage(System.getProperty("user.dir") + "/data/tasks.txt");
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (JarvisException e) {
            this.ui.printMessage("Error loading tasks.");
        }
    }

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
