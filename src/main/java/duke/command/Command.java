package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class representing commands that Duke program can run.
 * Each and every other command will inherit from Command super class.
 */
public abstract class Command {

    private boolean isEnd = false;

    /**
     * Task will be executed, data will be updated and saved, message will be printed to user.
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     * @return The String response of Duke after running command.
     * @throws IOException If there is an error when updating the file.
     */
    public abstract String run(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Sets the isEnd variable to true.
     * Duke program will end upon variable being set to true.
     */
    public void endApp() {
        this.isEnd = true;
    }

    /**
     * Checks if the command ends the program.
     * @return whether the program is ending.
     */
    public boolean getIsEnd() {
        return this.isEnd;
    }
}
