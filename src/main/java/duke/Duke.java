package duke;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHello();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while(!isExit) {
            try {
                isExit = Parser.parse(sc.nextLine(), tasks, ui, storage);

            } catch (DukeException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
