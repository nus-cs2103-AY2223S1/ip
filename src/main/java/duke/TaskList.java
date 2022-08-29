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
    public void delete(int index) throws IOException {
        System.out.println("Noted I have removed this task:");
        System.out.println(inputList.get(index - 1));
        Integer newSize = inputList.size() - 1;
        inputList.remove(index-1);
        System.out.println("Now you have " + newSize + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    /**
     * Method to mark a task as done.
     */
    public void mark(int index) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        inputList.get(index - 1).mark();
        System.out.println(inputList.get(index- 1));
        Storage.writeToFile(inputList);
    }

    /**
     * Method to mark a task as undone.
     */
    public void unmark(int index) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        inputList.get(index - 1).unmark();
        System.out.println(inputList.get(index- 1));
        Storage.writeToFile(inputList);
    }

    /**
     * Method to display the list of tasks.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:\n");
        for (Task item : inputList) {
            System.out.println(inputList.indexOf(item) + 1 + "." + item);
        }
    }

    /**
     * Method to store a Todo task in the list.
     * @param content Description of the Todo task.
     * @throws IOException
     */
    public void todo(String content) throws IOException {
        Todo td = new Todo(content);
        System.out.println("Got it. I've added this task:");
        System.out.println(td);
        inputList.add(td);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    /**
     * Method to store a Deadline task in the list.
     * @param content Description of the Deadline task.
     * @throws IOException
     */
    public void deadline(String content, String date) throws IOException {
        Deadline dl = new Deadline(content, date);
        System.out.println("Got it. I've added this task:");
        System.out.println(dl);
        inputList.add(dl);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    /**
     * Method to store an Event task in the list.
     * @param content Description of the Event task.
     * @throws IOException
     */
    public void event(String content, String date) throws IOException {
        Event ev = new Event(content, date);
        System.out.println("Got it. I've added this task:");
        System.out.println(ev);
        inputList.add(ev);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    /**
     * Method to get a task from the list.
     * @param index Index of the task.
     * @return The task of the index given.
     */
    public Task get(int index) {
        return this.inputList.get(index);
    }
}
