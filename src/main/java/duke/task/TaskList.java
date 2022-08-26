package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class which contains and operates on the task list
 */
public class TaskList {
    
    private final List<Task> taskList;

    /**
     * Initialises an empty list of tasks
     */
    public TaskList() { 
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises a preset list of tasks
     * 
     * @param taskList Preset list of tasks
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    public static void markAsDone(Task task) {
        task.isDone = true;
    }

    /**
     * Marks or unmarks task at specified index
     * 
     * @param action Either mark or unmark
     * @param taskIndex Index of task to mark
     */
    public void mark(String action, int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        task.isDone = action.equals("mark");
        System.out.println(
                (action.equals("mark")
                        ? "OK, I've marked this task as not done yet: \n"
                        : "Nice! I've marked this task as done: \n")
                        + task);
    }

    /**
     * Prints out the contents of the task list
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

    /**
     * Adds new task to task list
     * 
     * @param task Task to add
     */
    public void addNewTask(Task task) {
        taskList.add(task);
        System.out.println(
                "Got it. I've added this task: \n "
                        + task
                        + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Deletes task at index of task list
     * 
     * @param taskIndex Index of task to delete
     */
    public void deleteTask(int taskIndex) { 
        Task task = taskList.remove(taskIndex - 1);
        System.out.println(
                "Noted. I've removed this task:\n "
                        + task
                        + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Returns task list as data in disk storage
     * 
     * @return Data representation of task list
     */
    public String getDataList() {
        StringBuilder buffer = new StringBuilder();

        for (Task task : taskList) {
            buffer.append(task.toDataString()).append("\n");
        }
        
        return buffer.toString();
    }
    
}
