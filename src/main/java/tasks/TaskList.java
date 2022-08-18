package tasks;

import exceptions.*;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            Task item = tasks.get(i);
            System.out.println(j + "." + item.toString());
        }
    }

    private void printTask(int index) {
        System.out.println(tasks.get(index).toString());
    }

    private void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public void markDone(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new OutOfRangeException();
        } else {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            printTask(index);
        }
    }

    public void delete(String value) throws DukeException {
        int index = Integer.parseInt(value) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new OutOfRangeException();
        } else {
            System.out.println("Noted. I've removed this task:");
            printTask(index);
            tasks.remove(index);
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        }
    }

    public void addToDo(String desc) throws DukeException {
        if (desc.isBlank()) {
            throw new EmptyCommandException("todo");
        }
        ToDo newTask = new ToDo(desc);
        tasks.add(newTask);
        printAddedTask(newTask);
    }

    public void addDeadline(String desc, String time) throws DukeException{
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("deadline");
        }
        if (time == null || time.isBlank()) {
            throw new NoTimeException("deadline");
        }
        Deadline newTask = new Deadline(desc, time);
        tasks.add(newTask);
        printAddedTask(newTask);
    }

    public void addEvent(String desc, String time) throws DukeException{
        if (desc == null || desc.isBlank()) {
            throw new EmptyCommandException("event");
        }
        if (time == null || time.isBlank()) {
            throw new NoTimeException("event");
        }
        Event newTask = new Event(desc, time);
        tasks.add(newTask);
        printAddedTask(newTask);
    }
}