package main.java.duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public boolean getIsDone() {
            return isDone;
        }

        public String getDescription() {
            return description;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getSavedString() {
            return String.format("T | %s | %s\n",this.isDone, this.description);
        }

        @Override
        public String toString() {
            return String.format("[T] %s %s", Ui.checkbox(this.getIsDone()), this.getDescription());
        }
    }

    class Deadline extends Task {
        String deadline;
        LocalDate date;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        public Deadline(String description, LocalDate date) {
            super(description);
            this.date = date;
        }

        @Override
        public String getSavedString() {
            return String.format("D | %s | %s | %s\n",this.getIsDone(), this.getDescription(), this.deadline);
        }

        @Override
        public String toString() {
            return String.format("[D] %s %s (by: %s)", Ui.checkbox(this.getIsDone()), this.getDescription(), this.deadline);
        }
    }

    class Event extends Task {
        String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String getSavedString() {
            return String.format("E | %s | %s | %s\n",this.getIsDone(), this.getDescription(), this.time);
        }

        @Override
        public String toString() {
            return String.format("[E] %s %s (at: %s)", Ui.checkbox(this.getIsDone()), this.getDescription(), this.time);
        }
    }

    int pointer = 0;
    protected ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    public TaskList(TaskList taskList) {
        this.list = taskList.list;
    }

    public Task addTask(String description) {
        Task val = new Task(description);
        this.list.add(val);
//                this.list[pointer] = new Task(description);
        pointer++;
        return val;
    }

    public Deadline addDeadline(String desc, String deadline) {
        Deadline val = new Deadline(desc, deadline);
        this.list.add(val);
//                this.list[pointer] = new Deadline(desc, deadline);
        pointer++;
        return val;
    }

    public Deadline addDeadline(String desc, LocalDate date) {
        Deadline val = new Deadline(desc, date);
        this.list.add(val);
        pointer++;
        return val;
    }

    public Event addEvent(String desc, String time) {
        Event val = new Event(desc, time);
        this.list.add(val);
//                this.list[pointer] = new Event(desc, time);
        pointer++;
        return val;
    }

    public int getLength() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

    public void mark(int index) {
        this.get(index).mark();
    }

    public void unmark(int index) {
        this.get(index).unmark();
    }

    protected String saveData() {
        String result = "";
        for (Task task : this.list) {
            result += String.format("%s", task.getSavedString());
        }
        return result;
    }

    protected void printData() {
        this.list.forEach(x -> System.out.println(String.format("%s. %s", this.list.indexOf(x) + 1, x)));
    }
}
