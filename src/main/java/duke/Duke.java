package duke;

import duke.command.Command;
import duke.command.ExitCommand;

public class Duke {
    private Storage storage; 
    private TaskList tasks;
    private UI ui;
    
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage, ui);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                String output = c.execute(tasks);
                ui.print(output);
                if (c instanceof ExitCommand) break;
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks);
        } catch (DukeException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("././././data/duke.txt").run();
    }
}

// no access modifier: default, protected/private package? 