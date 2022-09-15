package poolsheen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import poolsheen.command.Command;

/**
 * Represents the Poolsheen CLI program.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version 0.2
 * @since 2022-08-15
 */
public class Poolsheen {
    /** The default file path for the save file. */
    private static final String SAVE_FILE_PATH = "./SAVE.TXT";

    /** The number of milliseconds Poolsheen takes to close the application. */
    private static final Integer LEAVE_TIME = 2000;

    /** Whether if this poolsheen object has stopped running */
    private static boolean hasExited;

    /** The object which Poolsheen uses to manage the save file. */
    private Storage storage;

    /** The object which handles all operations regarding tasks. */
    private TaskList listOfTasks;

    /** The object which handles all user interactions. */
    private Ui ui;

    /**
     * Initialises a Poolsheen object.
     */
    public Poolsheen() throws IOException {
        assert SAVE_FILE_PATH != null : "SAVE_FILE_PATH should not be null";
        this.hasExited = false;
        this.ui = new Ui();
        this.storage = new Storage(SAVE_FILE_PATH);
        try {
            this.listOfTasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            forceExit();
            throw new IOException("Cannot find save file while loading\n" + e.getMessage());
        } catch (PoolsheenException e) {
            ui.showLoadingError();
            throw new IOException("Error loading one of the tasks. Please check or delete your save file again.\n" + e);
        } catch (Exception e) {
            throw new IOException("An unexpected error has occurred whilst creating the Poolsheen object. "
                    + "Please check or delete your save file again.");
        }
    }

    /**
     * Forces the program to close and exit after a period of time.
     */
    public static void forceExit() {
        Poolsheen.hasExited = true;
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(LEAVE_TIME);
            } catch (InterruptedException e) {
                System.out.println("An error has occurred whilst leaving the program.");
            }
            Platform.exit();
        });
    }

    /**
     * Returns the string which is to be passed into the GUI under Poolsheen's response.
     * @param fullCommand The full string passed in by the user.
     * @return The string which is to be passed onto the GUI.
     */
    public String getResponse(String fullCommand) {
        String reply;
        try {
            Command c = Parser.parse(fullCommand);
            reply = c.execute(listOfTasks, ui, storage);
            storage.update(listOfTasks);
            if (hasExited) {
                Poolsheen.forceExit();
            }
        } catch (PoolsheenException e) {
            reply = e.toString();
        } catch (IndexOutOfBoundsException e) {
            reply = "An unexpected IndexOutOfBoundsException error has occurred." + e.getMessage();
        } catch (IOException e) {
            reply = "An unexpected IOException error has occurred when updating the save file!\n" + e.getMessage();
            Poolsheen.forceExit();
        } catch (NumberFormatException e) {
            reply = "An unexpected NumberFormat error has occurred!\n" + e.getMessage();
        } catch (Exception e) {
            reply = "The following unexpected error has occurred:\n" + e.getMessage();
            Poolsheen.forceExit();
        }
        return reply;
    }

    /**
     * Returns if the poolsheen program has ended.
     * @return A boolean if the program has ended.
     */
    public static boolean getExited() {
        return Poolsheen.hasExited;
    }
}
