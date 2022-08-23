package duke;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            try {
                Parser.parse(command, tasks);
            } catch (DukeException e) {
                ui.printMessage(e.toString());
            }
        }

        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.printMessage(e.toString());
        }

        ui.bye();

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
