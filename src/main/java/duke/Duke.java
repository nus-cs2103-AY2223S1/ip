package duke;

import duke.command.Command;

/**
 * Duke is a personal assistant Chat-bot that aims to help users to keep track of various things.
 */
public class Duke {
    private StorageList storageList;
    private Ui ui;
    private static final String DEFAULT_FILE_NAME = "default.txt";
    
    /**
     * Main method that runs the program.
     * @param args Arguments passed to the program
     */
    public static void main(String[] args) {
        new Duke(DEFAULT_FILE_NAME).run();
    }
    
    /**
     * Constructor for the Duke class.
     * @param filePath Path of the file to be used for the storage list
     */
    public Duke(String filePath) {
        storageList = new StorageList();
        ui = new Ui(storageList);
        try {
            FileIO.load(storageList, filePath);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        Output.GREETINGS.print();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandStr = ui.readCommand();
                Command command = Parser.parse(commandStr);
                command.execute(ui, storageList);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Returns the Default file name.
     * @return the DEFAULT_FILE_NAME
     */
    public static String getDefaultFileName() {
        return DEFAULT_FILE_NAME;
    }
}
