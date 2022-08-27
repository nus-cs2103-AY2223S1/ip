package kirby;

import kirby.commands.Command;
import kirby.exceptions.KirbyInvalidCommandException;
import kirby.exceptions.KirbyMissingArgumentException;
import java.io.*;

/**
 * The kirby.Kirby class implements the main method of the bot.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Kirby {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Kirby(String filePath, String dirPath) {
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

    public void run() throws KirbyInvalidCommandException, KirbyMissingArgumentException, IOException {
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

    public static void main(String[] args) throws IOException, KirbyInvalidCommandException, KirbyMissingArgumentException {
        String fileName = "data/kirby.txt";
        String dirName = "data/";
        Kirby kirby = new Kirby(fileName, dirName);
        kirby.run();
    }
}