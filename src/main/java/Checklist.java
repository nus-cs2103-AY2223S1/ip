import java.util.ArrayList;
import java.util.List;

class Checklist {
    private final List<Task> taskList = new ArrayList<>();

    void list() {
        System.out.println("Here are the tasks in your list: ");
        taskList.forEach(System.out::println);
    }

    void add(String task) {
        taskList.add(new Task(taskList.size() + 1, task));
    }

}
