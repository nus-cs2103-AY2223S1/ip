package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class FindCommand extends Command {
    String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Keyword that is to be found in all the tasks returned
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void run(TaskList taskList) {
        TaskList found = new TaskList();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task temp = taskList.retrieveTask(i);
            if (temp.getDescription().contains(keyword)) {
                found.addTask(temp);
            }
        }
        if (found.getSize() == 0) {
            System.out.println("No matching tasks were found :(");
        } else {
            for (int i = 1; i <= found.getSize(); i++) {
                System.out.println(i + ". " + found.retrieveTask(i - 1).toString());
            }
        }
    }
}
