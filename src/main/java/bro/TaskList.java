package bro;

import bro.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    Ui ui = new Ui();

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> task){
        this.tasks = task;
    }

    public void listAll(){
        int count = 1;
        for(Task t : this.tasks){
            System.out.println(count + "." + t.toString());
            count++;
        }
    }

    public void markTask(int n, Storage sto){
        this.tasks.get(n - 1).markAsDone();
        try {
            sto.modifyTaskFile(this.tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.markUi(tasks, n);
        ui.listSize(tasks);
    }

    public void unmarkTask(int n, Storage sto){
        this.tasks.get(n - 1).markAsNotDone();
        try {
            sto.modifyTaskFile(this.tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.unmarkUi(tasks, n);
        ui.listSize(tasks);
    }

    public void todoTask(Task t, Storage sto){
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

    public void deleteTask(int n, Storage sto){
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
