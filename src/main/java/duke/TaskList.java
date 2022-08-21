package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i).toString());
        }
    }

    public void deleteTask(int index) {
        Ui.deleteTask(this.list.get(index));
        this.list.remove(index);
        Ui.numOfTasks(this.list.size());
    }

    public void markTask(int index) {
        this.list.get(index).markAsDone();
        Ui.markTask(this.list.get(index));
    }

    public void unmarkTask(int index) {
        this.list.get(index).markAsUndone();
        Ui.unmarkTask(this.list.get(index));
    }

    public void addTodo(String description) {
        this.list.add(new Todo(description));
        Ui.addTask(this.list.get(list.size() - 1));
        Ui.numOfTasks(this.list.size());
    }

    public void addDeadline(String description, String by) {
        this.list.add(new Deadline(description, by));
        Ui.addTask(this.list.get(list.size() - 1));
        Ui.numOfTasks(this.list.size());
    }

    public void addEvent(String description, String when) {
        this.list.add(new Event(description, when));
        Ui.addTask(this.list.get(list.size() - 1));
        Ui.numOfTasks(this.list.size());
    }

    public int getSize() {
        return this.list.size();
    }
}
