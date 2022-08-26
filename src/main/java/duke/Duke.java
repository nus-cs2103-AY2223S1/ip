package duke;
import java.util.Scanner;

/**
 * Duke is a program that helps uses keep track of their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor for Duke.
     *
     * @param filePath string for path for file to read and write task list from
     */
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

    /**
     * Executes the program.
     */
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

        ui.sayBye();

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
