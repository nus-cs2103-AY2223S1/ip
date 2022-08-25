package poolsheen.command;

import java.util.ArrayList;

import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a Command which can be executed by the Poolsheen program.
 * Each command extends from this class.
 */
public abstract class Command {
    /** If this command will cause the program to stop running. */
    private boolean isEnder;

    /** The array list of strings that make up for the rest of the user input. */
    protected ArrayList<String> rest;

    /**
     * A protected constructor to create a Command object.
     * @param isEnder Determines if this command can cause the program to stop running.
     * @param rest The array list of strings that make up for the rest of the user input.
     */
    protected Command(boolean isEnder, ArrayList<String> rest) {
        this.isEnder = isEnder;
        this.rest = rest;
    }

    /**
     * Causes a change in the Poolsheen program.
     * @param tl The TaskList which the Poolsheen program will modify.
     * @param ui The Ui object which facilitates interactions between the user and the CLI.
     * @param storage The object which handles all interactions involving the save file.
     */
    public abstract void execute(TaskList tl, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isEnder;
    }
}
