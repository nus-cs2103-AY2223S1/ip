package drivers;

import commands.Command;
import exceptions.TumuException;

/**
 * Chatbot driver code. Receives the input from the user
 * and responds accordingly.
 */
public class Tumu {
    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    /**
     * Constructor for the Tumu class.
     */
    public Tumu() {
        ui = new UI();
        storage = new Storage("data/Tumu.txt");
        tasks = new TaskList(storage.loadData(ui));
    }

    /**
     * Main function to start execution of the program.
     * @param args Arguments during java command line execution.
     */
    public static void main(String[] args) {
        new Tumu().run();
    }

    /**
     * Run all the driver code to run the program.
     */
    private void run() {
        ui.greeting();
        response();
    }

    /**
     * Program comes up with a response to the command given
     * by the user.
     */
    private void response() {
        String command = "";
        do {
            String fullCommand = ui.readCommand();
            try {
                ui.showLine();
                Command c = Parser.parse(fullCommand, ui);
                command = Parser.getCommand(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (TumuException e) {
                ui.notifyUser(e.toString());
            } catch (NumberFormatException e) {
                ui.notifyUser("Please (un)mark or delete a task by " +
                        "its list position (must be an integer)!");
                ui.readCommand(); //flush rest of the commands
            } finally {
                ui.showLine();
            }
        } while (!command.equalsIgnoreCase("bye"));

        ui.closeReader();
    }
}
