package command;

import duke.TaskList;

/**
 *  A class which encapsulates the list command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class ListCommand extends Command {
    TaskList currList;

    public ListCommand(TaskList list) {
        this.currList = list;
    }

    /**
     * Executes the list command and shows the list of tasks.
     * @return A list of tasks as a string to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        String result = "Here are your tasks:\n";
        for (int i = 0; i < currList.getLength(); i++) {
            result += i + 1 + ". " + currList.getTaskAt(i).toString() + "\n";
        }
        return result;
    }
}
