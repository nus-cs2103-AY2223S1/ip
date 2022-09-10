package duke;

import duke.task.Task;
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
     * Constant used for printing indents.
     */
    private static String SPACING = "    ";

    /**
     * Constructor to create a TaskLIst when no previous data is available.
     */
    TaskList() { 
        this.taskList = new ArrayList<Task>();
        this.numTasks = 0;
    }

    /**
     * Overlaoded onstructor to create a TaskList when previous data is found.
     * @param taskList Takes in an ArrayList of Tasks loaded from previous use.
     */
    TaskList(ArrayList<Task> taskList) {
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
    public void addTask(Task newTask) {
        taskList.add(newTask);
        numTasks += 1;
        System.out.println(SPACING + "Got it. I've added this task:");
        System.out.println(SPACING + newTask.toString());
        System.out.println(SPACING + "Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
    }

    /**
     * Marks a task at the specified position to be done.
     * @param position Position of task to be marked.
     */
    public void markTask(int position) {
        if (position < numTasks) {
            Task task = taskList.get(position);
            task.setStatus(1);
            System.out.println(SPACING + "Nice! I have marked this task as done:");
            System.out.println(SPACING + "[" + task.getStatusIcon() +"] " + task.getTask());
        } else {
            System.out.println(SPACING + "No task at position " + Integer.toString(position + 1) + "!\n");
        }
    }

    /**
     * Marks a task at the specified position to be not done.
     * @param position Position of the task to be unmarked.
     */
    public void unmarkTask(int position) {
        if (position < numTasks) {
            Task task = taskList.get(position);
            task.setStatus(0);
            System.out.println(SPACING + "Nice! I have marked this task as undone:");
            System.out.println(SPACING + "[" + task.getStatusIcon() +"] " + task.getTask());
        } else {
            System.out.println(SPACING + "No task at position " + Integer.toString(position + 1) + "!\n");
        }
    }

    /**
     * Deletes the task at the specified position from the TaskList.
     * @param position Position of the task to be deleted.
     */
    public void deleteTask(int position) {
        if (numTasks == 0) { // No tasks in list
            System.out.println(SPACING + "List empty. Add tasks into your list!\n");
            
        } 
        else if (position > numTasks) {
            System.out.println(SPACING + "No such task!\n");
        } else { 
            Task deletedTask = taskList.remove(position - 1);
            numTasks -= 1;
            System.out.println(SPACING + "Noted. I've removed this task:");
            System.out.println(SPACING + deletedTask.toString());
            System.out.println(SPACING + "Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
        }
    }

    /**
     * Prints the entire TaskList.
     */
    public void printList() {
        System.out.println(SPACING + "Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println(SPACING + Integer.toString(i+1) + "." + taskList.get(i).toString());
        }
        System.out.println();
    }
}
