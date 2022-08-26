package duke;

import commands.Command;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Parser parser;

    /**
     * Creates a Duke object with the specified file path.
     * @param filePath The file path of the Duke.txt file where the last saved task list will be retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);
    }

    /**
     * Runs Duke, which starts the Duke chatbot.
     */
    public void run() {
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (parser.getIsOpen()) {
            String input = scanner.nextLine();
            try {
                parser.read(input).execute();
            } catch (DukeException error) {
                ui.showError(error);
            }
        }
        saveFile(tasks);
        ui.showSaved();
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Saves the current task list in Duke.txt.
     * @param tasks List of tasks to be saved.
     */
    public void saveFile(TaskList tasks) {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e);
        }
    }


    public static void main(String[] args) {
        new Duke("Duke.txt").run();
    }
}
