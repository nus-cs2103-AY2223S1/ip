package duke.command;

import duke.*;

/** A class to represent the command given to the bot, based on the user input. */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
