import command.Command;
import task_classes.Task;
import utils.FileIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {

    private boolean open = true;
    private ArrayList<Task> list;

    private Ui ui;

    public Duke() {
        this.ui = new Ui();

    }

    public void run() {
        this.ui.start();
        this.open = true;
        list = new ArrayList<>();
        for (Task t: FileIO.getInstance().readTaskList()) {
            list.add(t);
        }

        Scanner in = new Scanner(System.in);

        while (this.isOpen() && in.hasNext()) {
            String line = in.nextLine();
            Command c = ui.readCommand(line);
            ui.printWithHorizontalRule(c.exec(this.list));
        }
    }

    public boolean isOpen() {
        return this.open;
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
