package duke;

import java.util.Scanner;

public class Duke {
    private static final Scanner sc = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreetings();
        String str = sc.nextLine().trim();

        while(!str.equals("bye")) {
            try {
                Parser.parseInput(str, tasks);
            } catch (DukeException e) {
                ui.printMessage(e.toString());
            }
            str = sc.nextLine().trim();
        }
        try {
            storage.saveData(tasks.getTasks());
        } catch (DukeException e) {
            ui.printMessage(e.toString());
        }
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
