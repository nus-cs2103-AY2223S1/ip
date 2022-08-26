package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import duke.command.DukeCommandType;
import duke.ui.DukeUi;

/**
 * Encapsulates a task manager for Duke
 */
public class DukeTaskManager {
    private List<Task> dukeTasks;

    /**
     * Constructor for a new DukeTaskManager. Sets the current tasks to an empty list of task
     */
    public DukeTaskManager() {
        this.dukeTasks = new ArrayList<Task>();
    }

    /**
     * Constructor for a new DukeTaskManager. Sets the current tasks to the input tasks
     *
     * @param dukeTasks A list of tasks
     */
    public DukeTaskManager(List<Task> dukeTasks) {
        this.dukeTasks = dukeTasks;
    }

    /**
     * Returns the list of tasks currently stored
     */
    public List<Task> getTasks() {
        return dukeTasks;
    }

    /**
     * Adds a new task to the current list of tasks
     *
     * @param type Type of the task to be added
     * @param args Description of the task
     */
    public void dukeAddTask(DukeCommandType type, String args) {
        if (args.isEmpty()) {
            DukeUi.dukePrint("Description cannot be empty\n");
            return;
        }
        switch (type) {
        case TODO: {
            dukeAddToList(new Todo(args));
            return;
        }
        case DEADLINE: {
            try {
                Pattern p = Pattern.compile("(.+)/by(.+)");
                Matcher m = p.matcher(args);
                m.find();
                dukeAddToList(new Deadline(m.group(1).trim(), m.group(2).trim()));
            } catch (IllegalStateException e) {
                DukeUi.dukePrint("Are you missing a /by ?\n");
            }
            break;
        }
        case EVENT: {
            try {
                Pattern p = Pattern.compile("(.+)/at(.+)");
                Matcher m = p.matcher(args);
                m.find();
                dukeAddToList(new Event(m.group(1).trim(), m.group(2).trim()));
            } catch (IllegalStateException e) {
                DukeUi.dukePrint("Are you missing a /at ?\n");
            }
            break;
        }
        default: {
            return;
        }
        }
    }

    private void dukeAddToList(Task task) {
        dukeTasks.add(task);
        DukeUi.dukePrint(String.format("Got it. I've added this task: \n %s\n %s\n",
                task.toString(), getNoOfTasksRemaining()));
        return;
    }

    /**
     * Returns the number of tasks stored currently
     */
    protected int getNoOfTasks() {
        return dukeTasks.size();
    }

    /**
     * Returns the string representation of the tasks
     */
    protected String getNoOfTasksRemaining() {
        int size = getNoOfTasks();
        if (size <= 1) {
            return String.format("Now you have %d task in the list\n", size);
        } else {
            return String.format("Now you have %d tasks in the list\n", size);
        }
    }

    /**
     * Shows the list of tasks stored
     */
    public void dukeShowAllTasks() {
        String description = "List of tasks to be done:\n";
        dukeShowList(description, dukeTasks);
    }

    private void dukeShowList(String description, List<Task> tasks) {
        int size = tasks.size();
        int margin = String.valueOf(size).length();
        for (int i = 0; i < tasks.size(); i++) {
            description += String.format("%" + margin + "d. %s\n", i + 1, tasks.get(i));
        }
        DukeUi.dukePrint(description);
    }

    private void dukeMarkTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            dukeTasks.get(i).markComplete();
            String str = dukeTasks.get(i).toString();
            DukeUi.dukePrint(String.format("Nice! I've marked this task as done:\n %s\n", str));
        } else {
            DukeUi.dukePrint("Error. duke.task.Task is not in the list\n");
        }
    }

    private void dukeUnmarkTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            dukeTasks.get(i).markIncomplete();
            String str = dukeTasks.get(i).toString();
            DukeUi.dukePrint(String.format("OK, I've marked this task as not done yet:\n %s\n", str));
        } else {
            DukeUi.dukePrint("Error. Task is not in the list\n");
        }
    }

    private void dukeRemoveTask(int i) {
        if ((0 <= i) && (i < dukeTasks.size())) {
            Task remove = dukeTasks.remove(i);
            String str = remove.toString();
            DukeUi.dukePrint(String.format("OK, I've remove this task:\n %s\n %s", str, getNoOfTasksRemaining()));
        } else {
            DukeUi.dukePrint("Error. Task is not in the list\n");
        }
    }


    /**
     * Shows the list of tasks containing the query
     */
    public void dukeFindTasksContaining(String query) {
        String description = "Here are the matching tasks in your list:\n";
        dukeShowList(description,
                dukeTasks.stream().filter(task -> task.toString().contains(query)).collect(Collectors.toList()));
    }

    /**
     * Updates the status of the task to be completed,incomplete or the remove the task
     *
     * @param type The new status of the task
     * @param str String representation of the index of the tasks
     * */
    public void dukeUpdateTaskStatus(DukeCommandType type, String str) {
        try {
            int index = Integer.parseInt(str.split(" ")[0]) - 1;
            switch (type) {
            case MARK: {
                dukeMarkTask(index);
                break;
            }
            case UNMARK: {
                dukeUnmarkTask(index);
                break;
            }
            case DELETE: {
                dukeRemoveTask(index);
                break;
            }
            default: {
                return;
            }
            }
        } catch (NumberFormatException e) {
            DukeUi.dukePrint("Invalid index. Index is not a number\n");
        }
    }

}
