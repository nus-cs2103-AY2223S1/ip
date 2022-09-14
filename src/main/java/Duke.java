import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static TaskList taskList1;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {

        storage = new Storage(filePath);
        try {
            taskList = storage.loadTaskList();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        ui = new Ui(taskList, storage);
    }

    public void run() {
        ui.run();
    }


    public static void main(String[] args) {

        Duke duke = new Duke("data/task_list.txt");
        duke.run();
        // branch Level 8

    }
}
