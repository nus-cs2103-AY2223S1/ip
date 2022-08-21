import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public String list() {
        int len = tasks.size();
        StringBuilder stringBuilder = new StringBuilder("\tHere are the tasks in your list :D");
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            String task = "\n\t" + index + ". " + tasks.get(i);
            stringBuilder.append(task);
        }
        return stringBuilder.toString();
    }

    /**
     * Adds a Todo object into the list
     * @param message The task's description
     * @throws DukeException If the input is invalid
     */
    public void addTodo(String message) throws DukeException {
        if (message.length() == 0) {
            throw new DukeException("Please add using the following format: todo <description>");
        }
        ToDo todo = new ToDo(message);
        this.tasks.add(todo);
        String header = "\tGot it! I have added this task:\n\t\t" + todo;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");

        System.out.println(header + numOfTasks);
    }

    /**
     * Adds a Deadline object into the list
     * @param message The task's description and date
     * @throws DukeException If the input is invalid
     */
    public void addDeadline(String message) throws DukeException {
        String[] info = message.split(" /by ");
        if (info.length < 2) {
            throw new DukeException("Please add using the following format: " +
                    "deadline <description> /by <date>");
        }
        String description = info[0];
        String by = info[1];
        Deadline deadline = new Deadline(description, by);
        this.tasks.add(deadline);
        String header = "\tGot it! I have added this task:\n\t\t" + deadline;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");

        System.out.println(header + numOfTasks);
    }

    /**
     * Adds an Event object into the list
     * @param message The task's description and date
     * @throws DukeException If the input is invalid
     */
    public void addEvent(String message) throws DukeException {
        String[] info = message.split(" /at ");
        if (info.length < 2) {
            throw new DukeException("Please add using the following format: " +
                    "event <description> /at <date>");
        }
        String description = info[0];
        String at = info[1];
        Event event = new Event(description, at);
        this.tasks.add(event);
        String header = "\tGot it! I have added this task:\n\t\t" + event;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");

        System.out.println(header + numOfTasks);
    }

    public void delete(String message) throws DukeException {
        if (message.length() == 0) {
            throw new DukeException("I don't know which task to delete :(, " +
                    "please delete using the following format: delete <task-number>");
        }
        int taskNumber = Integer.parseInt(message);
        if (taskNumber > this.tasks.size()) {
            throw new DukeException("The task number exceeds the number of tasks in the list!");
        }

        Task task = tasks.get(taskNumber - 1);
        String msg = String.format("\tNoted, I have removed this task:\n\t\t%s", task);
        tasks.remove(taskNumber - 1);
        System.out.println(msg);
    }

    /**
     * Sets a task's completion status
     * @param id The task number
     * @param mark Whether to mark a task as completed
     */
    public void changeStatus(String id, boolean mark) {
        int index = Integer.parseInt(id) - 1;
        if (mark) {
            System.out.println(this.tasks.get(index).markAsDone());
        } else {
            System.out.println(this.tasks.get(index).markAsNotDone());
        }
    }

    public void getTasks(String date) throws DukeException {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            StringBuilder stringBuilder = new StringBuilder("\tYour tasks for today include:");
            int count = 1;
            for (Task task : this.tasks) {
                if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                    if (task.getDate().equals(parsedDate)) {
                        String formatted = String.format("\n\t%d. %s", count, task);
                        stringBuilder.append(formatted);
                        count++;
                    }
                }
            }
            if (count == 1) {
                System.out.println(String.format("\tNo tasks on %s",
                        parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));
            } else {
                System.out.println(stringBuilder);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please key in a valid date in this format: dd/MM/yyyy");
        }
    }
}
