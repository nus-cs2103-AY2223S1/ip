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
     */
    public Duke() {
        String filePath = "data/duke.txt";
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            String message = "";
            try {
                storage.save(tasks);
                message = Parser.parse(input, tasks);
            } catch (DukeException e) {
                message = e.toString();
            }
            return message;
        } else {
            try {
                return Parser.parse(input, tasks);
            } catch (DukeException e) {
                return e.toString();
            }
        }
    }
}
