package duke.util;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Controls what output to be printed to the console for the user to see
 */
public class Ui {
    /**
     * Displays goodbye message
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the added task and the updated length of the task list
     *
     * @param task the task added
     * @param tasks the list of task being modified
     */
    public String printAddedTask(Task task, TaskList tasks) {
        return ("Got it. I've added this task: \n" + task.toString()
                + "\nNow you have " + tasks.getLength()
                + " tasks in the list");
    }

    /**
     * Prints out when a task has been deleted, the task deleted
     * and the updated length of the task list
     *
     * @param task
     * @param tasks
     */
    public String printDeletedTask(Task task, TaskList tasks) {
        return (" Noted. I've removed this task: \n" + task.toString()
                + "\nNow you have " + tasks.getLength()
                + " tasks in the list");
    }

    /**
     * Prints out a message that responds to the user input
     *
     * @param response to be printed
     */
    public String printResponse(String response) {
        return response;
    }

    /**
     * Prints out all the tasks in the task list
     *
     * @param tasks the list to be printed
     */
    public String listTasks(TaskList tasks) {
        String reply = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getLength(); i++) {
            int index = i + 1;
            reply += index + ". " + tasks.getTask(i).toString() + "\n";
        }
        return reply;
    }

    /**
     * Prints out confirmation that a task has been marked as done
     *
     * @param task task marked as done
     * @return String confirmation
     */
    public String printMarked(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task.toString();
    }

    /**
     * Prints out confirmation that a task has been marked as not done
     *
     * @param task task marked as done
     * @return String confirmation
     */
    public String printUnmarked(Task task) {
        return "Nice! I've marked this task as not done yet: \n"
                + task.toString();
    }


    /**
     * Prints out the matching tasks
     *
     * @param tasks ArrayList of matching Tasks
     */
    public String printFoundTask(ArrayList<Task> tasks) {
        String reply = "Here are the matching tasks in your list:\n";
        String content = "";
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            content += index + ". " + task.toString() + "\n";
        }
        return content.isEmpty() ? "We couldnt find any task matching your search keyword :((" : reply + content;
    }

    /**
     * Prints out the list of commands available
     *
     */
    public String printHelp() {
        return "Welcome to Duke! Here are the commands you can type:" + "\n"
                + "Note the date should be in format" + "\n" + "yyyy-mm-dd HH:mm" + "\n"
                + "** To add new Tasks to your list, you can type: " + "\n"
                + "todo [description of your task]" + "\n"
                + "deadline [description of your task] + [date]" + "\n"
                + "event [description of your task] + [date]" + "\n"
                + "** To see all the tasks you added type: list" + "\n"
                + "** To see mark or unmark a task type: mark [index] or unmark [index]" + "\n"
                + "** To delete a task type: delete [index]" + "\n"
                + "** To find tasks containing certain words type: find [keywords]" + "\n"
                + "** To exit the program type: bye";
    }
}
