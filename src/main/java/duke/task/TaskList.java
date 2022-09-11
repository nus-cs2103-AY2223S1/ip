package duke.task;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> taskArray;
    private int count = 0;

    /**
     * Creates a new list of tasks
     */
    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    /**
     * Creates a new list of tasks using an existing ArrayList of tasks
     *
     * @param taskArray the list of tasks represented as an ArrayList
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    /**
     * Adds a task into the list of tasks
     *
     * @param task the task to be added
     */
    public void add(Task task){
        this.taskArray.add(task);
        this.count += 1;
    }

    /**
     * Returns the task at a particular index in the list of tasks
     *
     * @param position the index of the task in the list of tasks
     * @return the task at the given position
     * @throws IndexOutOfBoundsException if given position > length of list of tasks or given position < 1
     */
    public Task getTask(int position) throws IndexOutOfBoundsException{
        return taskArray.get(position - 1);
    }

    /**
     * Returns the total number of tasks in the list of tasks
     *
     * @return number of tasks in list
     */
    public int getCount(){
        return this.count;
    }

    /**
     * Marks a task at a particular position in the list of tasks as completed
     *
     * @param position the index of the task in the list of tasks
     * @throws IndexOutOfBoundsException if given position > length of list of tasks or given position < 1
     */
    public void markTaskAtPos(int position) throws IndexOutOfBoundsException{
            Task currTask = getTask(position);
            currTask.markAsDone();
    }

    /**
     * Unmarks a task at a particular position in the list of tasks, representing the task is incomplete
     *
     * @param position the index of the task in the list of tasks
     * @throws IndexOutOfBoundsException if given position > length of list of tasks or given position < 1
     */
    public void unmarkTaskAtPos(int position) throws IndexOutOfBoundsException{
            Task currTask = getTask(position);
            currTask.unmark();
    }

    /**
     * Deletes a task at a particular position in the list of tasks
     *
     * @param position the index of the task in the list of tasks
     * @return the deleted task
     * @throws IndexOutOfBoundsException the deleted task if given position > length of list of tasks or given position < 1
     */
    public Task deleteTaskAtPos(int position) throws IndexOutOfBoundsException {
        Task deletedTask = getTask(position);
        this.taskArray.remove(position - 1);
        this.count -= 1;
        return deletedTask;
    }

    /**
     * Returns the simple string representation of the list of tasks
     *
     * @return simple string representation
     */
    public String toSimpleStrings() {
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            stringedList += getTask(i + 1).toSimpleString() + "\n";
        }
        return stringedList;
    }

    /**
     * Returns the string representation of the list of tasks
     *
     * @return string representation
     */
    @Override
    public String toString(){
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            if (i == this.count -1) {
                stringedList += (i + 1) + ". " + getTask(i + 1).toString();
            } else {
                stringedList += (i + 1) + ". " + getTask(i + 1).toString() + "\n";
            }
        }
        return "Here are the tasks in your list:\n" + stringedList;
    }
}
