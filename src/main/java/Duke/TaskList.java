package Duke;
import java.util.ArrayList;
import java.util.Arrays;

import Duke.Task.Task;

/**
 * This class represents the task-list that allows users
 * to insert and remove different tasks, and also to mark or
 * unmark the tasks as completed.
 */
public class TaskList {

    /** This represents the task-list to be populated with tasks. */
    private ArrayList<Task> tasks = new ArrayList<>();

    /** Constructs tan empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    } 

    /**
     * Constructs the task list given a set of tasks.
     * 
     * @param tasks The current tasks to be put into task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    } 

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Inserts the given task into the task list.
     * 
     * @param task The task to be inserted.
     * @return True if the task completed successfully.
     */
    public boolean insertTask(Task task) {
        this.tasks.add(task);
        return true;
    }

    /**
     * Deletes task from the list.
     * 
     * @param taskID The id of the tasks to be deleted. The first task is of id 1.
     * @return The deleted tasks.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task[] delTask(int[] taskIDs) throws ArrayIndexOutOfBoundsException {
        Task[] deletedTasks;
        if (taskIDs.length == 1 && taskIDs[0] == -1) {
            Object[] taskObjects = this.tasks.toArray();
            deletedTasks = Arrays.copyOf(taskObjects, taskObjects.length, Task[].class);
            assert deletedTasks.length == tasks.size()
                    : "Number of tasks deleted should be same as number of input entries";
            this.tasks = new ArrayList<>();
        } else {
            deletedTasks = new Task[taskIDs.length];
            int numOfDeletedTask = 0;
            Arrays.sort(taskIDs);
            for (int i = 0; i < taskIDs.length; i++) {
                deletedTasks[i] = this.tasks.remove(taskIDs[i] - numOfDeletedTask);
                numOfDeletedTask += 1;
            }
            assert deletedTasks.length == taskIDs.length
                    : "Number of tasks deleted should be same as number of input entries";
        }
        return deletedTasks;
    }

    /**
     * Marks a task as completed.
     * 
     * @param taskID The id of the tasks to be marked. The first task is of id 1.
     * @return The marked tasks.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task[] markTask(int[] taskIDs) throws ArrayIndexOutOfBoundsException {
        Task[] markedTask;
        if (taskIDs.length == 1 && taskIDs[0] == -1) {
            for (int i = 0; i < tasks.size(); i++) {
                tasks.get(i).markTask();
            }
            Object[] taskObjects = this.tasks.toArray();
            markedTask = Arrays.copyOf(taskObjects, taskObjects.length, Task[].class);
            assert markedTask.length == tasks.size()
                    : "Number of tasks marked should be same as number of input entries";
        } else {
            markedTask = new Task[taskIDs.length];
            for (int i = 0; i < taskIDs.length; i++) {
                tasks.get(taskIDs[i]).markTask();
                markedTask[i] = tasks.get(taskIDs[i]);
            }
            assert markedTask.length == taskIDs.length
                    : "Number of tasks marked should be same as number of input entries";
        }
        return markedTask;
    }

    /**
     * Unmarks a task as completed.
     * 
     * @param taskID The id of the tasks to be unmarked. The first task is of id 1.
     * @return The unmarked tasks.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task[] unmarkTask(int[] taskIDs) throws ArrayIndexOutOfBoundsException {
        Task[] unmarkedTask;
        if (taskIDs.length == 1 && taskIDs[0] == -1) {
            for (int i = 0; i < tasks.size(); i++) {
                tasks.get(i).unmarkTask();
            }
            Object[] taskObjects = this.tasks.toArray();
            unmarkedTask = Arrays.copyOf(taskObjects, taskObjects.length, Task[].class);
            assert unmarkedTask.length == tasks.size()
                    : "Number of tasks unmarked should be same as number of input entries";
        } else {
            unmarkedTask = new Task[taskIDs.length];
            for (int i = 0; i < taskIDs.length; i++) {
                tasks.get(taskIDs[i]).unmarkTask();
                unmarkedTask[i] = tasks.get(taskIDs[i]);
            }
            assert unmarkedTask.length == taskIDs.length
                    : "Number of tasks unmarked should be same as number of input entries";
        }
        return unmarkedTask;
    }

    /**
     * Finds the list of alll the tasks whose description contains
     * the specified keyword.
     * 
     * @param keyword The keyword used to find the tasks.
     * @return The list of tasks that has description containing the
     * keyword.
     */
    public ArrayList<Task> findTasksContainingKeyword (String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.containsKeyword(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Print the output in customised format.
     * 
     * @param list The list to print
     * @return String representation of the task-list
     */
    @Override
    public String toString() {
        
        if (tasks.size() == 0) {
            String res = "\nHmm, you don't have any task yet!\n";
            res += "\nUse todo/event/deadline to start adding your task \uD83D\uDE00";
            return res;
        }
        String res = String.format("You have %d tasks in your list:", tasks.size());
        for (int i = 0; i <  tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            res += String.format("\n %d.%s", i + 1, tasks.get(i).toString());
        }
        return res;
    }

    public static String taskArrToString(Task[] tasksArr) {
        int i = 0;
        String res = "";
        if (tasksArr.length == 0) {
            return "Hmm, there's nothing here!";
        }
        for (Task task : tasksArr) {
            res += String.format("\n %d.%s", i + 1, task.toString());
        }
        return res;
    }

}
