package poolsheen;

import java.io.IOException;
import poolsheen.command.Command;

/**
 * Represents the Poolsheen CLI program.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */
public class Poolsheen {
    public static void main(String[] args) {
        new Poolsheen(SAVE_FILE_PATH).run();
    }

    private static final String SAVE_FILE_PATH = "SAVE.TXT";

    /** Whether if this poolsheen object has stopped running */
    private boolean hasExited;

    /** The object which Poolsheen uses to manage the save file. */
    private Storage storage;

    /** The object which handles all operations regarding tasks. */
    private TaskList listOfTasks;

    /** The object which handles all user interactions. */
    private Ui ui;

    /**
     * A private constructor to initialise the Poolsheen object.
     *
     * @param filePath The filePath which the Poolsheen program will depend on.
     */
    private Poolsheen(String filePath) {
        this.hasExited = false;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.listOfTasks = new TaskList(storage.load());
        } catch (PoolsheenException e) {
            ui.showLoadingError();
            this.listOfTasks = new TaskList();
        }
    }

    /**
     * Allows Poolsheen to listen to our user.
     */
    private void run() {
        ui.showWelcome();
        while (!this.hasExited) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(listOfTasks, ui, storage);
                storage.update(listOfTasks);
                this.hasExited = c.isExit();
            } catch (PoolsheenException e) {
                ui.showError(e.getMessage(), "PoolsheenException");
            } catch (IOException e) {
                ui.showError(e.getMessage(), "IOException");
            } catch (UnknownCommandException e) {
            ui.showError(e.toString(), "UnknownCommandException");
            } catch (IncompleteCommandException e) {
                ui.showError(e.toString(), "IncompleteCommandException");
            } catch (IndexOutOfBoundsException e) {
                ui.showError("Poolsheen thinks no task has this position.", "IndexOutOfBoundsException");
            } catch (NumberFormatException e) {
                ui.showError("Poolsheen believes this command needs an integer.", "NumberFormatException");
            } catch(IllegalArgumentException e) {
                ui.showError("Poolsheen has never seen this command and is confused.", "IllegalArgumentException");
            }  catch (Exception e) {
                System.out.println("An unexpected error has occurred and the program will end.");
                System.out.println("Error is: " + e.toString());
                this.exit();
            }
        }
        ui.showGoodbye();
    }

    /**
     * Cleans up leftover code before Poolsheen stops listening to our user.
     */
    private void exit() {
        this.hasExited = true;
    }
}
