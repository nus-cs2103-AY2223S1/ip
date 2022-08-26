package main.java.duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for TaskList components of Duke.
 */
public class TaskList {
    /**
     * Class for a singular Task.
     */
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

    /**
     * Class for a singular Deadline.
     */
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

    /**
     * Class for a singular Event.
     */
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
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    /**
     * Constructor for TaskList.
     * @param taskList - tasklist to copy data from
     */
    public TaskList(TaskList taskList) {
        this.list = taskList.list;
    }

    /**
     * Adds a task to the list.
     * @param description - description of the task
     */
    public Task addTask(String description) {
        Task val = new Task(description);
        this.list.add(val);
//                this.list[pointer] = new Task(description);
        pointer++;
        return val;
    }

    /**
     * Adds a deadline to the list.
     * @param desc - description of the task
     * @param deadline - the date of the deadline
     */
    public Deadline addDeadline(String desc, String deadline) {
        Deadline val = new Deadline(desc, deadline);
        this.list.add(val);
//                this.list[pointer] = new Deadline(desc, deadline);
        pointer++;
        return val;
    }

    /**
     * Adds a deadline to the list using a date.
     * @param desc - description of the task
     * @param date - the date of the deadline in LocalDate format
     */
    public Deadline addDeadline(String desc, LocalDate date) {
        Deadline val = new Deadline(desc, date);
        this.list.add(val);
        pointer++;
        return val;
    }

    /**
     * Adds an event to the list.
     * @param desc - description of the task
     * @param time - the time of the event
     */
    public Event addEvent(String desc, String time) {
        Event val = new Event(desc, time);
        this.list.add(val);
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

    /**
     * Marks a task as done.
     * @param index - index of the task to mark as done in the TaskList.
     */
    public void mark(int index) {
        this.get(index).mark();
    }

    /**
     * Marks a task as undone.
     * @param index - index of the task to mark as undone in the TaskList.
     */
    public void unmark(int index) {
        this.get(index).unmark();
    }

    /**
     * Returns a string representation of the TaskList for saving into a file.
     * @return - string representation of the TaskList for saving into a file.
     */
    protected String saveData() {
        String result = "";
        for (Task task : this.list) {
            result += String.format("%s", task.getSavedString());
        }
        return result;
    }

    /**
     * Prints the TaskList in a readable format.
     */
    protected void printData() {
        this.list.forEach(x -> System.out.println(String.format("%s. %s", this.list.indexOf(x) + 1, x)));
    }
}
