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

    /**
     * Adds task to taskList.
     * Used when loading tasks from storage.
     * 
     * @param task Task to be added to taskList.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Add ToDo task to taskList.
     * 
     * @param content Description of ToDo to be added.
     * @return String response after ToDo is added to the taskList.
     */
    public String addToDo(String content) {
        ToDo task = new ToDo(content);
        int prevListLen = this.list.size();
        this.list.add(task);

        assert prevListLen == this.list.size() + 1 : "List size should have increased by 1";

        return "Got it. I've added this task:\n" 
                + String.format("%s\n", task.toString()) 
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    /**
     * Adds Deadline task to taskList.
     * 
     * @param content Description of Deadline to be added.
     * @param date Date of Deadline
     * @param time Time of Deadline
     * @return String response after Deadline is added to the taskList.
     */
    public String addDeadline(String content, LocalDate date, LocalTime time){
        Deadline task = new Deadline(content, date, time);
        int prevListLen = this.list.size();
        this.list.add(task);

        assert prevListLen == this.list.size() + 1 : "List size should have decreased by 1";

        return "Got it. I've added this task:\n" 
                + String.format("%s\n", task.toString()) 
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    /**
     * Adds Event task to taskList.
     * 
     * @param content Description of Event to be added.
     * @param date Date of Event.
     * @param time Time of Event.
     * @return String response after Event is added to the taskList.
     */
    public String addEvent(String content, LocalDate date, LocalTime time){
        Event task = new Event(content, date, time);
        int prevListLen = this.list.size();
        this.list.add(task);

        assert prevListLen == this.list.size() + 1 : "List size should have decreased by 1";

        return "Got it. I've added this task:\n" 
                + String.format("%s\n", task.toString()) 
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    /**
     * Deletes task from taskList.
     * 
     * @param index Index of task to be deleted.
     * @return String response after task has been deleted.
     */
    public String deleteTask(int index) {
        Task task = null;
        try {
            task = this.list.get(index);
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be deleted does not exist.";
        }

        int prevListLen = this.list.size();

        this.list.remove(index);

        assert prevListLen == this.list.size() + 1 : "List size should have decreased by 1";

        return "Noted. I've removed this task:\n" 
                + String.format("%s\n", task.toString()) 
                        + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    /**
     * Unmarks task in taskList.
     * 
     * @param index Index of task to be unmarked.
     * @return String response after task has been unmarked.
     */
    public String unMarkTask(int index) {
        try {
            this.list.get(index).unMarkComplete();
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be unmarked does not exist.";
        }

        return "OK, I've marked this task as not done yet:\n" + this.list.get(index).toString();
    }

    /**
     * Marks task in taskList.
     * 
     * @param index Index of task to be marked.
     * @return String response after task has been marked.
     */
    public String markTask(int index) {
        try {
            this.list.get(index).markComplete();
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be marked does not exist.";
        }

        return "Nice! I've marked this task as done:\n" + this.list.get(index).toString();
    }

    /**
     * Prints all the tasks in taskList with correct formatting.
     * 
     * @return String response representing the tasks in the taskList.
     */
    public String printList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < this.list.size(); i++) {
            taskList.append(String.format("%d.%s\n", i + 1, this.list.get(i).toString()));
        }

        return taskList.toString();
    }

    /**
     * Produces a list of Strings representing the tasks to be written to storage.
     * 
     * @return List of Strings representing the tasks.
     */
    public List<String> produceWriteList() {
        ArrayList<String> writeList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            writeList.add(this.list.get(i).toFileData());
        }
        return writeList;
    }

    /**
     * Finds the task with description matching the given content.
     * 
     * @param content String that matches the task description.
     * @return String response representing the tasks in the taskList that match.
     */
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

    /**
     * Adds tag to the task in the taskList with the given index.
     * 
     * @param index Index of task to be tagged.
     * @param tag Description of the tag.
     * @return String response after task has been tagged.
     */
    public String tagTask(int index, String tag) {
        try {
            this.list.get(index).addTag(tag);
        } catch (IndexOutOfBoundsException e) {
            return "Task number to be tagged does not exist.";
        }
        return "Nice! I've tagged this task:\n" + this.list.get(index).toString();
    }
}
