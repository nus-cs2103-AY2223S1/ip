package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(Stream<String> lines) {
        taskList = populateList(lines);
    }

    List<Task> populateList(Stream<String> lines) {
        ArrayList<Task> arrayList = new ArrayList<>();
        lines.forEach(x -> {
            int id = Integer.parseInt(x.substring(0, 1));
            char type = x.charAt(3);
            boolean done = x.charAt(6) == 'X';
            String task;
            if (type == 'D' || type == 'E') {
                task = x.substring(9, x.indexOf("(") - 1);
                String additional = x.substring(x.indexOf("(") + 5);
                if (type == 'D') {
                    arrayList.add(new Deadline(id, task, additional, done));
                } else {
                    arrayList.add(new Event(id, task, additional, done));
                }
            } else {
                task = x.substring(9);
                arrayList.add(new Todo(id, task, done));
            }
        });
        return arrayList;
    }

    List<Task> retrieve() {
        return taskList;
    }

    void list() {
        System.out.println("Here are the tasks in your list: ");
        taskList.forEach(System.out::println);
    }

    void mark(int id, boolean done) {
        Task task = taskList.get(id - 1);
        if (done) {
            task.setDone();
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            task.setNotDone();
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        System.out.println(task);
    }

    void event(String name, String eventDate) {
        Event event = new Event(taskList.size() + 1, name, eventDate);
        taskList.add(event);
        System.out.println("Got it. I've added this task: ");
        System.out.println(event);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void deadline(String name, String deadline) {
        Deadline deadlineEvent = new Deadline(taskList.size() + 1, name, deadline);
        taskList.add(deadlineEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadlineEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void todo(String task) {
        Todo todo = new Todo(taskList.size() + 1, task);
        taskList.add(todo);
        System.out.println("Got it. I've added this task: ");
        System.out.println(todo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void delete(int id) {
        Task toRemove = taskList.remove(id - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(toRemove);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

}
