package duke.command;

import duke.*;


/**
 * Encapsulates a command to print out all tasks.
 */
public class ListCommand extends Command {

    /**
     * A function that executes prints out all the tasks
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui){
        return taskList.printTaskList();
    }
}