package Duke;

import Commands.Command;

import java.time.format.DateTimeParseException;

/**
 * Contains the logic for Duke
 */
public class Duke {
    private Storage storage;
    private TaskList lst;
    private Ui ui;
    public static final String filepath = "list.txt";

    /**
     * Constructor for Duke. Initializes UI, Storage and TaskList
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filepath);
        lst = new TaskList(storage.load());
    }

    /**
     * Returns the response from the Duke program
     *
     * @param command
     * @return message to be printed
     */
    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            String output = c.execute(lst, ui, storage);
            return output;
        } catch (Exception e) {
            return ui.printMsg(e.getMessage());
        }
    }

    /**
     * Runs the duke program
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(lst, ui, storage);
                isExit = c.isExit();
            } catch (DateTimeParseException e) {
                ui.printMsg(Constants.INVALID_DATE);
            } catch (Exception e) {
                ui.printMsg(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
