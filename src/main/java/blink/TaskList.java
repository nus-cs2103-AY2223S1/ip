package blink;

import blink.task.Deadlines;
import blink.task.Events;
import blink.task.Task;
import blink.task.ToDos;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int pos) {
        if (pos <= 0 || pos > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        Task temp = this.tasks.get(pos - 1);
        this.tasks.remove(pos - 1);
        return temp;
    }

    public int length() {
        return this.tasks.size();
    }

    public Task get(int x) {
        return this.tasks.get(x);
    }

    public String listTask() {
        String display = "";
        if (this.length() == 0) {
            return "No task currently";
        } else if (this.length() == 1) {
            display = display + "There is only 1 task currently:\n";
        } else {
            display = display = "There is a total of " + this.length() + " tasks currently:";
        }
        for (int x = 0; x < this.length(); x++) {
            display = display + "\n" + (x + 1) + ": " + this.get(x);
        }
        return display;
    }

    public void mark(int num) {
        if (num <= 0 || num > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        this.tasks.get(num - 1).mark();
    }

    public void unMark(int num) {
        if (num <= 0 || num > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        this.tasks.get(num - 1).unMark();
    }

    public String deleted() {
        if (this.length() == 0) {
            return "No tasks remaining";
        } else if (this.length() == 1) {
            return "1 task remains";
        } else {
            return this.length() + " tasks remaining";
        }
    }

    public ArrayList<Task> filter(LocalDate date) {
        ArrayList<Task> sameDates = new ArrayList<>();
        for (int x = 0; x < this.length(); x++) {
            Task temp = this.get(x);
            if (temp.checkDate(date)) {
                sameDates.add(temp);
            }
        }
        return sameDates;
    }

    public Events addEvent(String desc, String date) {
        Events temp = new Events(desc.strip(), date.strip());
        this.tasks.add(temp);
        return temp;
    }

    public Deadlines addDeadline(String desc, String date) {
        Deadlines temp = new Deadlines(desc.strip(), date.strip());
        this.tasks.add(temp);
        return temp;
    }

    public ToDos addTodo(String desc) {
        ToDos temp = new ToDos(desc.strip());
        this.tasks.add(temp);
        return temp;
    }

}
