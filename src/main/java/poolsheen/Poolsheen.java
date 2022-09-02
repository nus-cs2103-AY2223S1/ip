package poolsheen;

import java.io.IOException;

import poolsheen.command.Command;

/**
 * Represents the Poolsheen CLI program.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */
public class Poolsheen {
    private static final String SAVE_FILE_PATH = "SAVE.TXT";

    /** Whether if this poolsheen object has stopped running */
    private static boolean hasExited;

    /** The object which Poolsheen uses to manage the save file. */
    private Storage storage;

    /** The object which handles all operations regarding tasks. */
    private TaskList listOfTasks;

    /** The object which handles all user interactions. */
    private Ui ui;

    /**
     * A public constructor to initialise the Poolsheen object.
     */
    public Poolsheen() {
        this.hasExited = false;
        this.ui = new Ui();
        this.storage = new Storage(SAVE_FILE_PATH);
        try {
            this.listOfTasks = new TaskList(storage.load());
        } catch (PoolsheenException e) {
            ui.showLoadingError();
            this.listOfTasks = new TaskList();
        }
    }

    /**
     * Forces the program to exit immediately.
     */
    public static void forceExit() {
        Poolsheen.hasExited = true;
    }

    public String getResponse(String fullCommand) {
        String reply;
        try {
            Command c = Parser.parse(fullCommand);
            reply = c.execute(listOfTasks, ui, storage);
            storage.update(listOfTasks);
        } catch (PoolsheenException e) {
            reply = e.toString();
        } catch (IOException e) {
            reply = "An error has occurred when updating the save file!\n" + e.getMessage();
            Poolsheen.forceExit();
        } catch (NumberFormatException e) {
            reply = "An error has occurred. Please use a number instead.";
        } catch (Exception e) {
            reply = "The following error has occured:\n" + e.getMessage();
            Poolsheen.forceExit();
        }
        return reply;
    }

    /**
     * A getter method that returns if the poolsheen program has ended.
     * @return A boolean that is true if exitted, else false.
     */
    public static boolean getExited() {
        return Poolsheen.hasExited;
    }
}
