import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * deletes task from the list.
     *
     * @param
     * @param str
     */
    public void deleteTask(String str, ArrayList<Task> task) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= task.size()) {
            Task currTask = task.get(number - 1);
            task.remove(number - 1);
            System.out.println("Noted. I've removed this task:");
            String checkbox = String.format("%s", currTask);
            System.out.println(checkbox);
            System.out.printf("Now you have %d tasks int the list. \n", task.size());
        } else {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * marks tasks in the list
     *
     * @param
     * @param str
     */
    public void markTask(String str, ArrayList<Task> task) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= task.size()) {
            Task currTask = task.get(number - 1);
            currTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
            System.out.println(checkbox);
        } else {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * unmarks task on the list.
     *
     * @param
     * @param str
     */
    public void unmarkTask(String str, ArrayList<Task> task) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= task.size()) {
            Task currTask = task.get(number - 1);
            currTask.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
            System.out.println(checkbox);
        } else {
            throw new DukeException("Invalid task number!");
        }
    }
}
