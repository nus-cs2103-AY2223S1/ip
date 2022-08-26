package duke;

import duke.exception.EmptyDescException;
import duke.task.*;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    /**
     * Constructor.
     * @param s Initialisation of TaskList involves loading data from the file.
     */
    public TaskList(Scanner s) {
        taskArrayList = new ArrayList<>();
        while (s.hasNext()) {
            commandBuilder(s.nextLine());
        }
    }

    /**
     * Builds the command that determines which tasks to be added, according to the save file.
     * @param saveLine the line of data from the save file.
     */
    private void commandBuilder(String saveLine) {
        int divideIndex;
        String commandType = saveLine.substring(0, 1);
        int isDone = Integer.parseInt(saveLine.substring(4, 5));
        String commandDesc = saveLine.substring(8);

        switch(commandType) {
            case "T":
                try {
                    ToDo task = new ToDo(" " + commandDesc);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "D":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandBy = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Deadline task = new Deadline(commandDesc, commandBy);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandBy = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Event task = new Event(commandDesc, commandBy);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskArrayList.remove(index - 1);
    }

    /**
     * Marks a tast from the task list as done.
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) { taskArrayList.get(index-1).markDone(); }

    /**
     * Unmarks a test from the task list as not done yet.
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) { taskArrayList.get(index-1).unmarkDone(); }

    /**
     * Retrieves a task from the task list.
     * @param index The index of the task to be retrieved.
     * @return The retrieved task.
     */
    public Task getTask(int index) {
        return taskArrayList.get(index-1);
    }

    /**
     * @return The number of tasks in the task list.
     */
    public int listSize() {
        return taskArrayList.size();
    }

    /**
     * @return Iterator object that returns the tasks in the task list.
     */
    public ListIterator<Task> getIterator() {
        return taskArrayList.listIterator();
    }

    /**
     * Method to execute "list" command.
     * Prints out the tasks stored in the array list.
     */
    public void printTaskArray() {
        ListIterator<Task> iterate = taskArrayList.listIterator();
        System.out.println("Here are the tasks in your list:");
        int qty = 0;
        while (iterate.hasNext()) {
            qty++;
            System.out.println(qty + "." + iterate.next().toString());
        }
    }
}
