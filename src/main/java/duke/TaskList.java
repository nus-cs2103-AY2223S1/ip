package duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * deletes task from the list.
     *
     * @param task the task to be deleted
     * @param str description of the task
     */
    public String deleteTask(String str, TaskList task) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= task.size()) {
            Task currTask = task.get(number - 1);
            task.remove(number - 1);
            String result ="Noted. I've removed this task:\n";
            String checkbox = String.format("%s", currTask);
            result += checkbox;
            String line = String.format("\nNow you have %d tasks in the list. \n", task.size());
            result += line;
            return result;
        } else {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * marks tasks in the list
     *
     * @param task the task to be marked
     * @param str description of task
     */
    public String markTask(String str, TaskList task) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= task.size()) {
            Task currTask = task.get(number - 1);
            currTask.markAsDone();
            String result = "Nice! I've marked this task as done:\n";
            String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
            result += checkbox;
            return result;
        } else {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * unmarks task on the list.
     *
     * @param task the task to be unmarked
     * @param str the description of the task
     */
    public String unmarkTask(String str, TaskList task) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= task.size()) {
            Task currTask = task.get(number - 1);
            currTask.unmark();
            String result = "OK, I've marked this task as not done yet:\n";
            String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
            result += checkbox;
            return result;
        } else {
            throw new DukeException("Invalid task number!");
        }
    }
}
