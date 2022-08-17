import java.util.ArrayList;
import java.util.List;

class Checklist {
    private final List<Task> taskList = new ArrayList<>();

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
        System.out.println(task.toString());
    }

    void event(String name, String eventDate) {
        Event event = new Event(taskList.size() + 1, name, eventDate);
        taskList.add(event);
        System.out.println("Got it. I've added this task: ");
        System.out.println(event.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void deadline(String name, String deadline) {
        Deadline deadlineEvent = new Deadline(taskList.size() + 1, name, deadline);
        taskList.add(deadlineEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadlineEvent.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }


    void todo(String task) {
        Todo todo = new Todo(taskList.size() + 1, task);
        taskList.add(todo);
        System.out.println("Got it. I've added this task: ");
        System.out.println(todo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

}
