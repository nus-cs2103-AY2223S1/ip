package duke.task;

import java.util.ArrayList;

/**
 * The TaskList class is a class to store Tasks. The TaskList has methods to add, delete, mark, unmark
 * Tasks and to print the whole TaskList.
 */
public class TaskList {

    /**
     * Stores the Tasks in an ArrayList.
     */
    protected ArrayList<Task> taskList;

    /**
     * Stores the number of tasks currently.
     */
    protected int numTasks;

    /**
     * Constructor to create a TaskLIst when no previous data is available.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.numTasks = 0;
    }

    /**
     * Overloaded constructor to create a TaskList when previous data is found.
     * @param taskList Takes in an ArrayList of Tasks loaded from previous use.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numTasks = taskList.size();
    }

    /**
     * Getter for a task at a specific position.
     * @param position The position of the task to be retrieved in the ArrayList of Tasks.
     * @return Returns the task at the specified position.
     */
    public Task getItem(int position) {
        return this.taskList.get(position - 1);
    }

    /**
     * Getter for the tasks added by the user.
     * @return Returns an ArrayList of Tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Getter for the number of tasks added by the user.
     * @return Returns the number of tasks.
     */
    public int getListSize() {
        return this.numTasks;
    }

    /**
     * Adds a task.
     * @param newTask Task to be added.
     */
    public String addTask(Task newTask) {
        taskList.add(newTask);
        numTasks += 1;
        String output = "Got it. I've added this task:\n";
        output += newTask.toString() + "\n";
        output += "Now you have " + Integer.toString(numTasks) + " tasks in the list.\n";
        assert numTasks > 0;
        return output;
    }

    /**
     * Marks a task at the specified position to be done.
     * @param position Position of task to be marked.
     */
    public String markTask(int position) {
        if (position < numTasks) {
            Task task = taskList.get(position);
            task.setStatus(1);
            String output = "Nice! I have marked this task as done:\n";
            output += "[" + task.getStatusIcon() + "] " + task.getTask();
            return output;
        } else {
            return "No task at position " + Integer.toString(position + 1) + "!\n";
        }
    }

    /**
     * Marks a task at the specified position to be not done.
     * @param position Position of the task to be unmarked.
     */
    public String unmarkTask(int position) {
        if (position < numTasks) {
            Task task = taskList.get(position);
            task.setStatus(0);
            String output = "Nice! I have marked this task as undone:\n";
            output += "[" + task.getStatusIcon() + "] " + task.getTask();
            return output;
        } else {
            return "No task at position " + Integer.toString(position + 1) + "!\n";
        }
    }

    /**
     * Deletes the task at the specified position from the TaskList.
     * @param position Position of the task to be deleted.
     */
    public String deleteTask(int position) {
        if (numTasks == 0) { // No tasks in list
            return "Task list empty. Add tasks into your list!\n";
        }
        else if (position > numTasks) {
            return "No such task!\n";
        } else {
            Task deletedTask = taskList.remove(position - 1);
            numTasks -= 1;
            String output = "Noted. I've removed this task:\n";
            output += deletedTask.toString() + "\n";
            output += "Now you have " + Integer.toString(numTasks) + " tasks in the list.";
            return output;
        }
    }

    /**
     * Searches for tasks in the TaskList containing the keyword.
     * @param keyword Keyword to be searched in the tasks.
     */
    public String find(String keyword) {
        String output = "Here are the matching tasks in your list:";
        int matchesFound = 0;
        for (int i = 0; i < numTasks; i++) {
            Task task = taskList.get(i);
            String taskDescription = task.getTask();
            if (taskDescription.contains(keyword)) {
                output += "\n" + Integer.toString(i + 1) + "." + task.toString();
                matchesFound += 1;
            }
        }
        if (matchesFound > 0) {
            assert output.contains(keyword);
            return output;
        } else {
            return "No matches found!";
        }
    }

    /**
     * Prints the entire TaskList.
     */
    public String printList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < numTasks; i++) {
            output += Integer.toString(i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return output;
    }
}