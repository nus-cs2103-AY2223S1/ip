package duke;


import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> inputList;

    public TaskList(ArrayList<Task> inputList) {
        this.inputList = inputList;
    }

    public TaskList() {
        this.inputList = new ArrayList<>();
    }

    public void delete(int index) throws IOException {
        System.out.println("Noted I have removed this task:");
        System.out.println(inputList.get(index - 1));
        Integer newSize = inputList.size() - 1;
        inputList.remove(index-1);
        System.out.println("Now you have " + newSize + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    public void mark(int index) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        inputList.get(index - 1).mark();
        System.out.println(inputList.get(index- 1));
        Storage.writeToFile(inputList);
    }

    public void unmark(int index) throws IOException {
        System.out.println("Nice! I've marked this task as done:");
        inputList.get(index - 1).unmark();
        System.out.println(inputList.get(index- 1));
        Storage.writeToFile(inputList);
    }

    public void list() {
        System.out.println("Here are the tasks in your list:\n");
        for (Task item : inputList) {
            System.out.println(inputList.indexOf(item) + 1 + "." + item);
        }
    }

    public void todo(String content) throws IOException {
        Todo td = new Todo(content);
        System.out.println("Got it. I've added this task:");
        System.out.println(td);
        inputList.add(td);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    public void deadline(String content, String date) throws IOException {
        Deadline dl = new Deadline(content, date);
        System.out.println("Got it. I've added this task:");
        System.out.println(dl);
        inputList.add(dl);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        Storage.writeToFile(inputList);
    }

    public void event(String content, String date) throws IOException {
        Event ev = new Event(content, date);
        System.out.println("Got it. I've added this task:");
        System.out.println(ev);
        inputList.add(ev);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        Storage.writeToFile(inputList);
    }
}
