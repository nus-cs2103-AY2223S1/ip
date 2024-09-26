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
        int tagIndex;
        int isDone;
        String commandType;
        String commandDesc;
        String tagDesc = "";

        commandType = saveLine.substring(0, 1);
        isDone = Integer.parseInt(saveLine.substring(4, 5));
        commandDesc = saveLine.substring(8);
        tagIndex = commandDesc.lastIndexOf("#");
        if (tagIndex != -1) {
            tagDesc = commandDesc.substring(tagIndex - 1);
            commandDesc = commandDesc.replace(tagDesc, "");
        }

        switch(commandType) {
            case "T":
                try {
                    ToDo task = new ToDo(" " + commandDesc);
                    task.loadTag(tagDesc);
                    if (isDone == 1) { task.markDone(); }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "D":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandByDate = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Deadline task = new Deadline(commandDesc, commandByDate);
                    task.loadTag(tagDesc);
                    if (isDone == 1) {
                        task.markDone();
                    }
                    taskArrayList.add(task);
                } catch (EmptyDescException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "E":
                try {
                    divideIndex = commandDesc.lastIndexOf("|");
                    String commandAtDate = commandDesc.substring(divideIndex + 2);
                    commandDesc = " " + commandDesc.substring(0 , divideIndex - 1);
                    Event task = new Event(commandDesc, commandAtDate);
                    task.loadTag(tagDesc);
                    if (isDone == 1) {
                        task.markDone();
                    }
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
    public void markTask(int index) {
        taskArrayList.get(index-1).markDone();
    }

    /**
     * Unmarks a test from the task list as not done yet.
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        taskArrayList.get(index-1).unmarkDone();
    }

    public void tagInTaskList(int index, String tagDesc) {
        taskArrayList.get(index-1).tagTask(tagDesc);
    }

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
}
