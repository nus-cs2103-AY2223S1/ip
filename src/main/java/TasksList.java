import java.util.List;
import java.util.ArrayList;

public class TasksList {
    private final List<Task> tasksList;

    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Add To-do task to the list of tasks
     *
     * @param description The task to be added to the list
     */
    public void addTodo(String description) {
        System.out.println("Got it! Duke has added this task:");
        Task newTask = new ToDo(description);
        System.out.println(newTask);
        this.tasksList.add(newTask);
        int len = this.tasksList.size();
        String line = String.format("Now you have %d task%s in the list.",
                len, len > 1 ? "s" : "");
        System.out.println(line);
    }

    /**
     * Add Deadline task to the list of tasks
     *
     * @param description The task to be added to the list
     */
    public void addDeadline(String description, String date) {
        System.out.println("Got it! Duke has added this task:");
        Task newTask = new Deadline(description, date);
        System.out.println(newTask);
        this.tasksList.add(newTask);
        int len = this.tasksList.size();
        String line = String.format("Now you have %d task%s in the list.",
                len, len > 1 ? "s" : "");
        System.out.println(line);
    }

    /**
     * Add Event task to the list of tasks
     *
     * @param description The task to be added to the list
     */
    public void addEvent(String description, String date) {
        System.out.println("Got it! Duke has added this task:");
        Task newTask = new Event(description, date);
        System.out.println(newTask);
        this.tasksList.add(newTask);
        int len = this.tasksList.size();
        String line = String.format("Now you have %d task%s in the list.",
                len, len > 1 ? "s" : "");
        System.out.println(line);
    }

    /**
     * Prints out the list of the history of tasks
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        if (tasksList.size() == 0) {
            System.out.println("*No tasks! ^_^*");
            return;
        }
        for (int i = 0; i < tasksList.size(); i++) {
            String line = String.format("%d. %s", i + 1, tasksList.get(i));
            System.out.println(line);
        }
    }

    /**
     * Mark the task of the given id as done.
     *
     * @param id The id of the task to be marked done.
     */
    public void markTask(int id) {
        int len = this.tasksList.size();
        if (id <= 0 || id > len) {
            System.out.println("Invalid task id!");
        } else {
            this.tasksList.get(id - 1).mark();
        }
    }

    /**
     * Mark the task of the given id as not done.
     *
     * @param id The id of the task to be marked as not done.
     */
    public void unmarkTask(int id) {
        int len = this.tasksList.size();
        if (id <= 0 || id > len) {
            System.out.println("Invalid task id!");
        } else {
            this.tasksList.get(id - 1).unmark();
        }
    }
}
