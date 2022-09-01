package duke;

import java.util.List;
import java.util.ArrayList;

/**
 *  Contains the task list that is generated from our stored data, or newly created
 *  Has operations to update the status of our tasks or add/delete them
 */
public class TaskList {
    private int numOfTasks;
    private static List<Task> tasks;
    private String indent = "    ";

    public TaskList() {
        this.numOfTasks = 0;
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.numOfTasks = taskList.size();
        this.tasks = taskList;
    }

    List<Task> getTasks() {
        return this.tasks;
    }

    String add(Task task) {
        String response = "";
        tasks.add(task);
        response += indent + "Got it. I've added this task:" + "\n";
        response += "    " + task + "\n";
        numOfTasks += 1;
        response +=  indent + "Now you have " + numOfTasks + " tasks in the list";
        return response;
    }

    String delete(int taskIndex) {
        String response = "";
        numOfTasks -= 1;
        response += "Noted. I've removed this task:" + "\n";
        response += "      " + tasks.get(taskIndex - 1) + "\n";
        response += indent + "Now you have " + numOfTasks + " tasks in the list";
        tasks.remove(taskIndex - 1);
        return response;
    }

    String mark(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.mark();
        return indent + "Nice! I've marked this task as done:" + "\n      " + task;
    }

    String unmark(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.unmark();
        return indent + "Ok, I've marked this task as not done yet:" + "\n       " + task;
    }

    String printTasks() {
        String taskList = "Here are your tasks: \n";
        for (int j = 0; j < numOfTasks; j++) {
            int taskNum = j + 1;
            taskList += indent + taskNum + ". " + tasks.get(j) + "\n";
        }
        return taskList;
    }

    /**
     * Prints out all the tasks in our task list associated with the given keyword by the user
     *
     * @param keyword input user is trying to find
     * @throws DukeException if none of our tasks description contains the keyword
     */
    String findTasks(String keyword) throws DukeException{
        // initiate a boolean variable to check if the keyword exists in our task list
        String response = "";
        boolean isFindable = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                response += task + "\n";
                isFindable = true;
            }
        }

        //Throw a DukeException that there exist no such keyword if we cannot find it in our task list
        if (!isFindable) {
            throw new DukeException("There exists no such keyword in the task list!");
        }
        return response;
    }
}
