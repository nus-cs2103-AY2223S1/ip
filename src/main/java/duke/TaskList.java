package duke;

import duke.task.Task;
import java.util.ArrayList;


public class TaskList {
    protected ArrayList<Task> taskList;
    protected int numTasks;
    private static String SPACING = "    ";

    // Constructor to create a task list when no previous list is available
    TaskList() { 
        this.taskList = new ArrayList<Task>();
        this.numTasks = 0;
    }

    // 
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numTasks = taskList.size();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getListSize() {
        return this.numTasks;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        numTasks += 1;
        System.out.println(SPACING + "Got it. I've added this task:");
        System.out.println(SPACING + newTask.toString());
        System.out.println(SPACING + "Now you have " + Integer.toString(numTasks) + " tasks in the list.\n");
    }

    public void markTask(int position) {
        if (position < numTasks) {
            Task task = taskList.get(position);
            task.setStatus(1);
            System.out.println(SPACING + "Nice! I have marked this task as done:");
            System.out.println(SPACING + "[" + task.getStatusIcon() +"] " + task.getTask());
        } else {
            System.out.println(SPACING + "No task at position " + Integer.toString(position) + "!\n");
        }
    }

    public void unmarkTask(int position) {
        if (position < numTasks) {
            Task task = taskList.get(position);
            task.setStatus(0);
            System.out.println(SPACING + "Nice! I have marked this task as undone:");
            System.out.println(SPACING + "[" + task.getStatusIcon() +"] " + task.getTask());
        } else {
            System.out.println(SPACING + "No task at position " + Integer.toString(position) + "!\n");
        }
    }

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

    public void printList() {
        System.out.println(SPACING + "Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println(SPACING + Integer.toString(i+1) + "." + taskList.get(i).toString());
        }
        System.out.println();
    }
}
