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
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Task;
import alpha.task.Todo;

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
    public String generateCommandExecutionMessage(Command command, TaskList taskList, Task task, int taskNumber) {
        int numberOfTasksLeft = taskList.getTaskList().size();
        String taskLeft = "tasks";
        if (numberOfTasksLeft == 1) {
            taskLeft = "task";
        }
        String date = "";
        if (task != null) {
            if (!(task instanceof Todo)) {
                date = getDateOrDeadline(task);
            }
        }
        if (command instanceof Add) {
            if (date.equals("")) {
                return (">> " + "added: " + task.getDescription() + "\nNow you have " + numberOfTasksLeft
                        + " " + taskLeft + " in the list!");
            } else {
                return (">> " + "added: " + task.getDescription() + " (" + date + ")"
                        + "\nNow you have " + numberOfTasksLeft + " " + taskLeft + " in the list!");
            }
        } else if (command instanceof Delete) {
            if (date.equals("")) {
                return (">> " + "deleted: " + task.getDescription() + "\nNow you have " + numberOfTasksLeft
                        + " " + taskLeft + " in the list!");
            } else {
                return (">> " + "deleted: " + task.getDescription() + " (" + date + ")"
                        + "\nNow you have " + numberOfTasksLeft + " " + taskLeft + " in the list!");
            }
        } else if (command instanceof Exit) {
            return (">> Come back later to continue from where you left!\n See you, Bye!");
        } else if (command instanceof Help) {
            return "COMMAND\t" + "FORMAT\n\n"
                    + "1. todo\n\t\t\t" + "todo description\n"
                    + "2. event\n\t\t\t" + "event description /on yyyy-mm-dd\n"
                    + "3. deadline\n\t\t\t" + "deadline description /by yyyy-mm-dd\n"
                    + "4. mark\n\t\t\t" + "mark task_number\n"
                    + "5. unmark\n\t\t\t" + "unmark task_number\n"
                    + "6. tag\n\t\t\t" + "tag task_number /as tag_name\n"
                    + "7. find\n\t\t\t" + "find keyword\n"
                    + "8. findTag\n\t\t\t" + "findTag tag\n"
                    + "9. delete\n\t\t\t" + "delete task_number\n"
                    + "10. list\n\t\t\t" + "list\n"
                    + "11. bye\n\t\t\t" + "bye";
        } else if (command instanceof Mark) {
            if (date.equals("")) {
                return (">> " + "marked: " + task.getDescription());
            } else {
                return (">> " + "marked: " + task.getDescription() + " (" + date + ")");
            }
        } else if (command instanceof Tag) {
            String tagAdded = task.getTag();
            if (date.equals("")) {
                return (">> " + "tagged: " + task.getDescription() + " as " + tagAdded);
            } else {
                return (">> " + "tagged: " + task.getDescription() + " (" + date + ")" + " as " + tagAdded);
            }
        } else {
            assert command instanceof Unmark;
            if (date.equals("")) {
                return (">> " + "unmarked: " + task.getDescription());
            } else {
                return (">> " + "unmarked: " + task.getDescription() + " (" + date + ")");
            }
        }
    }

    /**
     * Returns a string containing the date or deadline of the given task.
     * @param task Task whose date/deadline is needed.
     * @return Date/Deadline of the task.
     */
    private String getDateOrDeadline(Task task) {
        if (task instanceof Event) {
            Event e = (Event) task;
            return e.getDate();
        } else {
            Deadline d = (Deadline) task;
            return d.getDeadline();
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
