package duke.tasklist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.ui.Ui;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void addToDo(String content) {
        ToDo task = new ToDo(content);
        this.list.add(task);
        Ui.addTaskMessage(task.toString(), this.list.size());
    }

    public void addDeadline(String content, LocalDate date, LocalTime time){
        Deadline task = new Deadline(content, date, time);
        this.list.add(task);
        Ui.addTaskMessage(task.toString(), this.list.size());
    }

    public void addEvent(String content, LocalDate date, LocalTime time){
        Event task = new Event(content, date, time);
        this.list.add(task);
        Ui.addTaskMessage(task.toString(), this.list.size());
    }

    public void deleteTask(int index) {
        Task task = null;
        try {
            task = this.list.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be deleted does not exist.");
            return;
        }
        this.list.remove(index);
        Ui.removeTaskMessage(task.toString(), this.list.size());
    }

    public void unmarkTask(int index) {
        try {
            this.list.get(index).unmarkComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be unmarked does not exist.");
            return;
        }
        Ui.unmarkTaskMessage(this.list.get(index).toString());
    }

    public void markTask(int index) {
        try {
            this.list.get(index).markComplete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Task number to be marked does not exist.");
            return;
        }
        Ui.markTaskMessage(this.list.get(index).toString());
    }

    public void printList() {
        Ui.showLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, this.list.get(i).toString()));
        }
        Ui.showLine();
    }

    public ArrayList<String> produceWriteList() {
        ArrayList<String> writeList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            writeList.add(this.list.get(i).toFileData());
        }
        return writeList;
    }
}
