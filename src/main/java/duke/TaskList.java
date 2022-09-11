package duke;


import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that creates an object with a list of data of the tasks.
 *
 * @author A0235287X
 */
public class TaskList {

    private ArrayList<Task> inputList;

    /**
     * Constructor that creates a TaskList object with a list of tasks.
     * @param inputList A list of tasks.
     */
    public TaskList(ArrayList<Task> inputList) {
        this.inputList = inputList;
    }

    /**
     * Constructor that creates a TaskList object without a list of tasks.
     */
    public TaskList() {
        this.inputList = new ArrayList<>();
    }

    /**
     * Method to add a task into the list of tasks.
     * @param t Task to be added.
     * @throws IOException
     */
    public void add(Task t) throws IOException {
        this.inputList.add(t);
        Storage.writeToFile(inputList);
    }

    /**
     * Method to remove a task from the list of tasks.
     * @param index The index of the task in the list that has to be removed.
     * @throws IOException
     */
    public String delete(int index) throws IOException {
        Task deletedTask = inputList.remove(index - 1);
        Integer newSize = inputList.size();
        String response = "Noted I have removed this task:\n" + deletedTask
                            + "\nNow you have " + newSize + " tasks in the list.";
        Storage.writeToFile(inputList);
        return response;
    }

    /**
     * Method to mark a task as done.
     */
    public String mark(int index) throws IOException {
        assert index < inputList.size() || index > 0;
        inputList.get(index - 1).mark();
        String response = "Nice! I've marked this task as done:\n" + inputList.get(index- 1);
        Storage.writeToFile(inputList);
        return response;
    }

    /**
     * Method to mark a task as undone.
     */
    public String unmark(int index) throws IOException {
        assert index < inputList.size() || index > 0;
        inputList.get(index - 1).unmark();
        String response = "Nice! I've marked this task as undone:\n" + inputList.get(index- 1);
        Storage.writeToFile(inputList);
        return response;
    }

    /**
     * Method to display the list of tasks.
     */
    public String list() {
        String itemList = "";
        String response = "";
        for (Task item : inputList) {
            itemList += inputList.indexOf(item) + 1 + ". " + item + "\n";
        }
        if (inputList.size() == 0) {
            response += "There are no tasks in your list.!";
            return response;
        }

        if (inputList.size() == 1) {
            response += "Here is the task in your list:\n" + itemList;
            return response;
        }
        response += "Here are the tasks in your list:\n" + itemList;
        return response;
    }

    /**
     * Method to store a Todo task in the list.
     * @param content Description of the Todo task.
     * @throws IOException
     */
    public String todo(String content) throws IOException {
        Todo td = new Todo(content);
        inputList.add(td);
        String response = "Got it. I've added this task: " + td.toString() + "\n"
                + "Now you have " + inputList.size() + " tasks in the list.";
        Storage.writeToFile(inputList);
        return response;
    }

    /**
     * Method to store a Deadline task in the list.
     * @param content Description of the Deadline task.
     * @throws IOException
     */
    public String deadline(String content, String date) throws IOException {
        Deadline dl = new Deadline(content, date);
        inputList.add(dl);
        String response = "Got it. I've added this task: " + dl.toString() + "\n"
                + "Now you have " + inputList.size() + " tasks in the list.";
        Storage.writeToFile(inputList);
        return response;
    }

    /**
     * Method to store an Event task in the list.
     * @param content Description of the Event task.
     * @throws IOException
     */
    public String event(String content, String date) throws IOException {
        Event ev = new Event(content, date);
        inputList.add(ev);
        String response = "Got it. I've added this task: " + ev.toString() + "\n"
                            + "Now you have " + inputList.size() + " tasks in the list.";
        Storage.writeToFile(inputList);
        return response;
    }

    /**
     * Method to get a task from the list.
     * @param index Index of the task.
     * @return The task of the index given.
     */
    public Task get(int index) {
        return this.inputList.get(index);
    }

    /**
     * Method to search for tasks that have the keyword that is given by user.
     * @param str Keyword given by user.
     */
    public String find(String str) {
        ArrayList<Task> list = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).toString().contains(str)) {
                list.add(inputList.get(i));
            }
        }
        String itemList = "";
        for (Task item : list) {
            itemList += list.indexOf(item) + 1 + "." + item.toString() + "\n";
        }

        assert !itemList.isEmpty();

        String response = "Here are the matching tasks in your list:\n" + itemList;
        return response;
    }
}
