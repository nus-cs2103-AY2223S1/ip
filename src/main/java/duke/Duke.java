package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            System.out.println(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isDone = false;
        while (!isDone) {
            try {
                String input = ui.parseCommand();
                Command c = Parser.parse(input);
                c.handle(this.storage, this.ui, this.taskList);
                isDone = c.isDone();
            } catch (DukeException e) {
                ui.line();
                System.out.println(e);
                ui.line();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
