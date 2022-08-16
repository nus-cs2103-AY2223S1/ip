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

    void event(String input) {
        String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
        String eventDate = input.substring(input.indexOf("/") + 4);
        taskList.add(new Event(taskList.size() + 1, name, eventDate));
    }

    void deadline(String input) {
        String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
        String deadline = input.substring(input.indexOf("/") + 4);
        taskList.add(new Deadline(taskList.size() + 1, name, deadline));
    }


    void todo(String task) {
        taskList.add(new Todo(taskList.size() + 1, task));
    }

}
