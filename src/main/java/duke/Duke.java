package duke;

import duke.command.Command;
import duke.command.ExitCommand;
/**
 * Represents Duke the chatbot, which is the essence of the program.
 */
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

    /**
     * Executes the entire process from displaying the welcome message,
     * receiving and handling all user inputs, to exiting the program.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                String output = c.execute(tasks);
                ui.print(output);
                if (c instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Gets the response for a particular input command.
     *
     * @param input User's input.
     * @return The program's response to the user's input.
     */
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
