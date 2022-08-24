package duke;

import java.util.List;
import java.util.ArrayList;

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

    void add(Task task) {
        tasks.add(task);
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println("    " + task);
        numOfTasks += 1;
        System.out.println(indent + "Now you have " + numOfTasks + " tasks in the list");
    }

    void delete(int taskIndex) {
        numOfTasks -= 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("      " + tasks.get(taskIndex - 1));
        System.out.println(indent + "Now you have " + numOfTasks + " tasks in the list");
        tasks.remove(taskIndex - 1);
    }

    void mark(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.mark();
        System.out.println(indent + "Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    void unmark(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.unmark();
        System.out.println(indent + "Ok, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }

    void printTasks() {
        for (int j = 0; j < numOfTasks; j++) {
            int taskNum = j + 1;
            System.out.println(indent + taskNum + ". " + tasks.get(j));
        }
    }

    /**
     * Prints out all the tasks in our task list associated with the given keyword by the user
     *
     * @param keyword input user is trying to find
     * @throws DukeException if none of our tasks description contains the keyword
     */
    void findTasks(String keyword) throws DukeException{
        // initiate a boolean variable to check if the keyword exists in our task list
        boolean isFindable = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(task);
                isFindable = true;
            }
        }

        //Throw a DukeException that there exist no such keyword if we cannot find it in our task list
        if (!isFindable) {
            throw new DukeException("There exists no such keyword in the task list!");
        }
    }
}
