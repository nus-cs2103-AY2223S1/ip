import task_classes.Task;
import utils.FileIO;

import java.util.Scanner;

public class Duke {

    private Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    public void run() {
        this.ui.start();
        for (Task t: FileIO.getInstance().readTaskList()) {
            tasks.add(t);
        }

        Scanner in = new Scanner(System.in);

        while (this.ui.isOpen() && in.hasNext()) {
            String line = in.nextLine();
            Command c = ui.readCommand(line);
            ui.printWithHorizontalRule(c.exec(this.tasks));
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
