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
    private final Ui ui;

    /**
     * Constructor for the Tumu class.
     */
    public Tumu() {
        ui = new Ui();
        storage = new Storage("data/Tumu.txt");
        tasks = new TaskList(storage.loadData(ui));
    }

    /**
     * Run the greeting message.
     */
    public String runGreeting() {
        return ui.greeting();
    }

    /**
     * Program comes up with a response to the command given
     * by the user.
     */
    public String getResponse(String input) {
        String output = new String();
        String fullCommand = input;
        try {
            Command c = Parser.parse(fullCommand, ui);
            assert(c != null);
            output = c.execute(tasks, ui, storage);
        } catch (TumuException e) {
            output += ui.notifyUser(e.toString());
        } catch (NumberFormatException e) {
            output += ui.notifyUser("Please (un)mark or delete a task by "
                    + "its list position (must be an integer)!");
        } catch (NullPointerException e) {
            output += ui.notifyUser("Please type a task description for the task!");
        }

        return output;
    }

    public static void main(String[] args) {
        Launcher.launch(args);
    }
}
