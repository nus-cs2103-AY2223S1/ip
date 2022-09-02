package bro;

import bro.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    Ui ui = new Ui();

    /**
     * Constructor that initialises tasks variable.
     */
    public TaskList(){
        tasks = new ArrayList<>();
    }

    /**
     * Constructor that initialises tasks variable with the given task.
     */
    public TaskList(ArrayList<Task> task){
        this.tasks = task;
    }

    /**
     * Lists all the task in the ArrayList tasks.
     */
    public void listAll(){
        int count = 1;
        for(Task t : this.tasks){
            System.out.println(count + "." + t.toString());
            count++;
        }
    }

    /**
     * Marks the task as done by setting isDone boolean to true.
     * @param n Index of the task to be marked.
     * @param sto Storage location of the file.
     */
    public void markTask(int n, Storage sto) {
        this.tasks.get(n - 1).markAsDone();
        try {
            sto.modifyTaskFile(this.tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.markUi(tasks, n);
        ui.listSize(tasks);
    }

    /**
     * Unmarks the task as done by setting isDone boolean to false.
     * @param n Index of the task to be unmarked.
     * @param sto Storage location of the file.
     */
    public void unmarkTask(int n, Storage sto) {
        this.tasks.get(n - 1).markAsNotDone();
        try {
            sto.modifyTaskFile(this.tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.unmarkUi(tasks, n);
        ui.listSize(tasks);
    }

    /**
     * Adds todo task to the list of the tasks.
     * @param t Task to be added
     * @param sto Storage location of the file.
     */
    public void todoTask(Task t, Storage sto) {
        t.markAsNotDone();
        tasks.add(t);
        try {
            sto.writeToFile(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printAdd(t);
        ui.listSize(tasks);
    }

    /**
     * Adds deadline task to the list of the tasks.
     * @param t Task to be added
     * @param sto Storage location of the file.
     */
    public void deadlineTask(Task t, Storage sto) {
        t.markAsNotDone();
        tasks.add(t);
        try {
            sto.writeToFile(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printAdd(t);
        ui.listSize(tasks);
    }

    /**
     * Adds event task to the list of the tasks.
     * @param t Task to be added
     * @param sto Storage location of the file.
     */
    public void eventTask(Task t, Storage sto) {
        t.markAsNotDone();
        tasks.add(t);
        try {
            sto.writeToFile(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printAdd(t);
        ui.listSize(tasks);
    }

    /**
     * Deletes the task from the task list.
     * @param n Index of the task to be deleted.
     * @param sto Storage location of the file.
     */
    public void deleteTask(int n, Storage sto) {
        ui.deleteUi(tasks, n);
        tasks.remove(n - 1);
        try {
            sto.modifyTaskFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.listSize(tasks);
    }
}
