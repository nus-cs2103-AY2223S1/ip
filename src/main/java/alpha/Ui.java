package alpha;

import java.util.List;

import alpha.command.Add;
import alpha.command.Command;
import alpha.command.Delete;
import alpha.command.Exit;
import alpha.command.Help;
import alpha.command.Mark;
import alpha.command.Tag;
import alpha.command.Unmark;
import alpha.task.Task;

/**
 * Displays messages.
 */
public class Ui {

    /**
     * Returns text of the required colour.
     *
     * @param command Command for which the execution message is to be generated.
     * @param task Task for which the command was executed.
     * @param taskNumber Task number of the task on which the command was executed.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public String generateCommandExecutionMessage(Command command, Task task, int taskNumber) {
        if (command instanceof Add) {
            return (">> " + "added task: " + task.getDescription());
        } else if (command instanceof Delete) {
            return (">> " + "deleted task: " + taskNumber);
        } else if (command instanceof Exit) {
            return (">> Come back later to continue from where you left!\n See you, Bye!");
        } else if (command instanceof Help) {
            return "COMMAND\t" + "FORMAT\n"
                    + "1. todo    \t\t" + "todo description\n"
                    + "2. event   \t\t" + "event description /on yyyy-mm-dd\n"
                    + "3. deadline\t" + "deadline description /by yyyy-mm-dd\n"
                    + "4. mark    \t\t" + "mark task_number\n"
                    + "5. unmark  \t" + "unmark task_number\n"
                    + "6. delete  \t\t" + "delete task_number\n"
                    + "7. list    \t\t" + "list\n"
                    + "8. find    \t\t" + "find keyword/tag\n"
                    + "9. tag     \t\t" + "tag task_number /as tag";
        } else if (command instanceof Mark) {
            return (">> " + "marked task: " + taskNumber);
        } else if (command instanceof Tag) {
            return (">> " + "tagged task: " + taskNumber);
        } else {
            assert command instanceof Unmark;
            return (">> " + "unmarked task: " + taskNumber);
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @param  taskList Object of TaksList class that contains the list of tasks to be printed.
     * @return A string containing a list of tasks.
     */
    public String generateTaskListToBePrinted(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        if (tasks.isEmpty()) {
            return (">> " + "Your task list is empty!");
        } else {
            String tasksToBePrinted = "";
            tasksToBePrinted += (">> "
                    + "Your task list is as follows:\n");
            int count = 1;
            for (Task task : tasks) {
                tasksToBePrinted += (count + ") "
                        + task.toString() + "\n");
                count++;
            }
            return tasksToBePrinted;
        }
    }
}
