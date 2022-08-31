package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;

/**
 *  Command object that is created when a recognized command is input.
 */
abstract public class Command {

     /**
      * Runs the command to get the desired outcome.
      *
      * @param tasks TaskList object of current Blink object
      * @param ui Ui object of current Blink object
      * @param storage Storage object of current Blink object
      */
     public abstract String execute(TaskList tasks, Ui ui, Storage storage);

     /**
      * Checks if the command run will cause the program to terminate.
      *
      * @return True if ending and false if it's not
      */
     public abstract boolean isExit();
}
