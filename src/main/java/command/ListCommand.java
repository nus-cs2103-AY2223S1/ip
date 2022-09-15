package command;

import duke.TaskList;

/**
 * An abstract class that represents ListCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor for ListCommand Object
     */
    public ListCommand(TaskList taskList) {

        this.taskList = taskList;
    }

    /**
     * Returns a string of the executed ListCommand
     * @return a string after the execution of ListCommand
     */
    @Override
    public String execute() {

        return taskList.list();
    }
}
