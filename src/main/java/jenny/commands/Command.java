package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;

import java.util.ArrayList;

/**
 * Abstract class for all other different types of commands.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public abstract class Command {
    protected String arguments;

    /**
     * Constructor for an instance of command.
     * Will initialise a new command with the provided {@code arguments}.
     *
     * @param arguments valid command arguments defined.
     */
    public Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns true if the command is an exit command,
     * returns false otherwise.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the current command.
     *
     * @param tasks   the instance of {@link TaskList} to run the command with.
     * @param ui      the instance of {@link Ui} to run the command with.
     * @param storage the instance of {@link Storage} to run the command with.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public abstract void run(TaskList tasks, Ui ui, Storage<ArrayList<Task>> storage) throws JennyException;
}
