package pikachu;

import pikachu.command.Command;

/**
 * Represents the Pikachu task manager bot. A <code>Pikachu</code> object corresponds to
 * a chatbot to manager user task, with a storage space, a task list and a Ui to communicate with the user
 */
public class Pikachu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pikachu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns whether the string being put in is numeric or not.
     *
     * @param str String to be checked whether it is numeric.
     * @return boolean value indicating if string is numeric.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Runs the Pikachu task manager bot.
     */
    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PikachuException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        
    }

    /**
     * Runs the Pikachu task manager bot with a location to put the stored task data.
     */
    public static void main(String[] args) {
        new Pikachu("/Users/xuyi/Documents/CS2103T/ip/data/pikachu.txt").run();
    }
}
