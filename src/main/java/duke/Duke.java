package duke;

import duke.command.Command;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
               ui.displayError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }

}
