package duke.tasklist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public String addToDo(String content) {
        ToDo task = new ToDo(content);
        this.list.add(task);
        return "Got it. I've added this task:\n"
                + String.format("%s\n", task.toString())
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    public String addDeadline(String content, LocalDate date, LocalTime time){
        Deadline task = new Deadline(content, date, time);
        this.list.add(task);
        return "Got it. I've added this task:\n"
                + String.format("%s\n", task.toString())
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    public String addEvent(String content, LocalDate date, LocalTime time){
        Event task = new Event(content, date, time);
        this.list.add(task);
        return "Got it. I've added this task:\n"
                + String.format("%s\n", task.toString())
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    public String deleteTask(int index) {
        Task task = null;
        try {
            task = this.list.get(index);
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be deleted does not exist.";
        }

        this.list.remove(index);
        return "Noted. I've removed this task:\n"
                + String.format("%s\n", task.toString())
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    public String unMarkTask(int index) {
        try {
            this.list.get(index).unMarkComplete();
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be unmarked does not exist.";
        }

        return "OK, I've marked this task as not done yet:\n" + this.list.get(index).toString();
    }

    public String markTask(int index) {
        try {
            this.list.get(index).markComplete();
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be marked does not exist.";
        }

        return "Nice! I've marked this task as done:\n" + this.list.get(index).toString();
    }

    public String printList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < this.list.size(); i++) {
            taskList.append(String.format("%d.%s\n", i + 1, this.list.get(i).toString()));
        }

        return taskList.toString();
    }

    public List<String> produceWriteList() {
        ArrayList<String> writeList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            writeList.add(this.list.get(i).toFileData());
        }
        return writeList;
    }

    public String find(String content) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).toString().toLowerCase().contains(content.toLowerCase())) {
                matchList.add(this.list.get(i));
            }
        }

        StringBuilder matches = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchList.size(); i++) {
            matches.append(String.format("%d.%s\n", i + 1, matchList.get(i).toString()));
        }

        return matches.toString();
    }
}
