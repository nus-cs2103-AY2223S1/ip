package wanya.task;

import wanya.WanyaException;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<String> data) {
        this();
        for (String encodedTask : data) {
            String[] inputs = encodedTask.split("\\|");
            String taskType = inputs[0];
            boolean completed = inputs[1].equals("1");
            String description = inputs[2];
            switch(taskType) {
            case "T":
                ToDo toDo = new ToDo(description, completed);
                tasks.add(toDo);
                break;
            case "D":
                String dueDate = inputs[3];
                Deadline deadline = new Deadline(description, completed, dueDate);
                tasks.add(deadline);
                break;
            case "E":
                String date = inputs[3];
                Event event = new Event(description, completed, date);
                tasks.add(event);
                break;
            }
        }
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void addToDo(String taskName)  {
        Task newTask = new ToDo(taskName);
        addTask(newTask);
    }

    public void addDeadline(String commandInput) throws WanyaException, DateTimeException {
        String[] inputs = commandInput.split("/by");
        //no deadline provided
        if (inputs.length == 1) {
            throw new WanyaException("Deadline must have a due date\n" +
                    "Include '/by' and date with format " +
                    "\"yyyy-mm-dd\" at the back");
        }
        String taskName = inputs[0];
        String dueDate = inputs[1].trim();
        Task newTask = new Deadline(taskName, dueDate);
        addTask(newTask);
    }

    public void addEvent(String commandInput) throws WanyaException, DateTimeException {
        String[] inputs = commandInput.split("/at");
        //no date provided
        if (inputs.length == 1) {
            throw new WanyaException("Event must have a date\n" +
                    "Include '/at' and date with format " +
                    "\"yyyy-mm-dd\" at the back");
        }
        String taskName = inputs[0];
        String date = inputs[1].trim();
        Task newTask = new Event(taskName, date);
        addTask(newTask);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("You have added: \n" + task);
        System.out.println("Now you have " + size() + " tasks in your list. \n");
    }

    public void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("WWaku WWaku!!! Wanya has used her magic powers to remove this task:\n" + task);
        System.out.println("Now you have " + size() + " tasks in your list. \n");
    }

    public void showTasks() {
        if (isEmpty()) {
            System.out.println("List is empty! Wheee slack time!");
        }
        for (int i = 1; i <= size(); i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + "." + task);
        }
        System.out.println("");
    }

    public List<String> saveToStorage() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            Task task = tasks.get(i);
            data.add(task.toStorageString());
        }
        return data;
    }

    /**
     * Finds a list of tasks that contains the keyword and print it.
     *
     * @param keyword word to find matching tasks.
     */
    public void findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task: tasks) {
            String taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        System.out.println("Here are the matching tasks in your list:");
        for (Task task: matchingTasks) {
            System.out.println(task);
        }
    }
}
