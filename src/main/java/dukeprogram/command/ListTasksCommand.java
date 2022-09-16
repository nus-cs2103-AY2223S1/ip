package dukeprogram.command;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dukeprogram.Duke;
import dukeprogram.facilities.TaskList;

/**
 * A ListTaskCommand specifies a command that allows the main program
 * to list out all the tasks presently stored in the task list.
 */
public class ListTasksCommand extends Command {

    /**
     * Creates a ListTasksCommand
     * @param duke the instance of duke that spawned this command
     */
    public ListTasksCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) {
        printToGui();
    }

    /**
     * Sends a message to the window of the Duke instance to list
     * all the tasks to the GUI window
     */
    public void printToGui() {
        TaskList currentTaskList = duke.getTaskList();

        String formattedTaskListString = IntStream.range(0, currentTaskList.getSize())
                .mapToObj(i -> i + 1 + ". " + currentTaskList.get(i).toString())
                .collect(Collectors.joining("\n"));

        duke.sendMessage("Here is your task list:\n" + formattedTaskListString);
    }
}
