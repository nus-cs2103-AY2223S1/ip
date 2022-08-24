package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import duke.domain.Task;
import duke.exceptions.TaskNotFoundException;

/**
 * The type Task controller.
 */
public class TaskController {

    private final Set<Task> taskSet;
    private final List<Task> taskList;

    /**
     * Instantiates a new Task controller.
     */
    public TaskController() {
        this.taskSet = new HashSet<>();
        this.taskList = new ArrayList<>();
    }

    /**
     * Marks a task from the list of tasks given its index.
     *
     * @param idx Index of Task in List
     * @throws TaskNotFoundException Thrown if task is not found
     */
    public void markTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(idx);
            curr.setComplete();

            System.out.println("I've marked this task as done:");
            System.out.println(curr);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Unmarks a task from the list of tasks given its index.
     *
     * @param idx Index of Task in List
     * @throws TaskNotFoundException Thrown if task is not found
     */
    public void unmarkTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(idx);
            curr.setIncomplete();

            System.out.println("I've  unmarked this task as done:");
            System.out.println(curr);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param newTask Task to be added
     */
    public void addTask(Task newTask) {
        boolean addFlag = taskSet.add(newTask);
        if (addFlag) {
            taskList.add(newTask);
        }

        System.out.println("\n___________________________ \n");
        System.out.println("Your wish is my command. I've added this task:");
        System.out.println(newTask);
        System.out.println("___________________________ \n");
    }

    /**
     * Deletes a task from the list of tasks given its index.
     *
     * @param idx Index of Task in List
     * @throws TaskNotFoundException Thrown if task is not found
     */
    public void deleteTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(idx);
            taskList.remove(curr);
            taskSet.remove(curr);

            System.out.println("\n___________________________ \n");
            System.out.println(
                "Your wish is my command. I've deleted this task:"
            );
            System.out.println(curr);
            System.out.println("___________________________ \n");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Load tasks.
     *
     * @param initTask the init task
     */
    public void loadTasks(List<Task> initTask) {
        this.taskList.addAll(initTask);
        this.taskSet.addAll(initTask);
    }

    /**
     * Export task list list.
     *
     * @return the list
     */
    public List<String> exportTaskList() {
        List<String> result = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task != null) {
                result.add(task.exportString());
            }
        }
        return result;
    }

    /**
     * List all tasks in the list of tasks.
     */
    public void listTasks() {
        System.out.println("\n___________________________ \n");
        System.out.println("Here are the tasks in your list\n");
        for (int idx = 0; idx < taskList.size(); idx++) {
            System.out.println(idx + 1 + ": " + taskList.get(idx));
        }
        System.out.println("\n___________________________ \n");
    }

    /**
     * List tasks.
     *
     * @param inputDateTime the input date time
     * @param isBefore      the is before
     */
    public void listTasks(String inputDateTime, boolean isBefore) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd-MM-yyyy HH:mm"
            );
            LocalDateTime dateTime = LocalDateTime.parse(
                inputDateTime,
                formatter
            );
            System.out.println("\n___________________________ \n");
            System.out.println("Here are the tasks in your list\n");
            int counter = 1;
            for (Task task : taskList) {
                if (isBefore && task.isBefore(dateTime)) {
                    System.out.printf("%d: %s%n", counter, task);
                    counter++;
                }
                if (!isBefore && task.isAfter(dateTime)) {
                    System.out.printf("%d: %s%n", counter, task);
                    counter++;
                }
            }
            System.out.println("\n___________________________ \n");
            // Stream<Task> taskStream;
            // if (isBefore) {
            // taskStream = this.taskList.stream().filter(x -> x.isBefore(dateTime));
            // } else {
            // taskStream = this.taskList.stream().filter(x -> x.isAfter(dateTime));
            // }

            // IntStream.iterate(1, x -> x + 1).map(x -> )

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
