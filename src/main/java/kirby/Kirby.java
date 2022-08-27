package kirby;

import kirby.commands.Command;
import kirby.exceptions.KirbyInvalidCommandException;
import kirby.exceptions.KirbyMissingArgumentException;
import java.io.*;

/**
 * Kirby class implements the main method.
 */
public class Kirby {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the class Kirby.
     *
     * @param filePath name of the file path as a string.
     * @param dirPath name of the directory path as a string.
     * @throws FileNotFoundException if the file mentioned is not found.
     * @throws IOException if there is an improper input error.
     */
    public Kirby(String filePath, String dirPath) throws FileNotFoundException, IOException {
        ui = new Ui();
        try {
            storage = new Storage(filePath, dirPath);
            // if there is a storage file
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the program.
     *
     * @throws KirbyInvalidCommandException if an undefined command is entered.
     * @throws KirbyMissingArgumentException if the command has invalid arguments.
     */
    public void run() throws KirbyInvalidCommandException, KirbyMissingArgumentException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KirbyInvalidCommandException | KirbyMissingArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Runs the main method.
     *
     * @throws KirbyInvalidCommandException if an undefined command is entered.
     * @throws KirbyMissingArgumentException if the command has invalid arguments.
     * @throws IOException if there is an improper input error.
     */
    public static void main(String[] args) throws IOException, KirbyInvalidCommandException, KirbyMissingArgumentException {
        String fileName = "data/kirby.txt";
        String dirName = "data/";
        Kirby kirby = new Kirby(fileName, dirName);
        kirby.run();
    }
}