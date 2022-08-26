import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class represents the todo-list that allows users
 * to insert and remove different tasks, and also to mark or
 * unmark the tasks as completed.
 */
public class TaskList {

    /** This represents the todo-list to be populated with tasks */
<<<<<<< HEAD
    private ArrayList<Task> items = new ArrayList<>();

    public static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

    /**
     * Insert a new task into the todo-list.
     * 
     * @param newItem The task to be added
     * @return Prompts indicating the result of the insertion
     * @throws ArrayIndexOutOfBoundsException
     */
    public String insertItem(String newItem) 
            throws ArrayIndexOutOfBoundsException {

        if (newItem.split(" ", 2).length < 2 && newItem.equals("todo")) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        } else if (newItem.split(" ", 2).length < 2 && newItem.equals("deadline")) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.";
        } else if (newItem.split(" ", 2).length < 2 && newItem.equals("event")) {
            return "☹ OOPS!!! The description of a event cannot be empty.";
        } else if (newItem.split(" ", 2).length < 2) {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        String taskType = newItem.split(" ", 2)[0].trim();
        String taskDetails = newItem.split(" ", 2)[1].trim();

        if (taskType.equalsIgnoreCase("deadline")) {
            if (taskDetails.split("/by").length < 2) {
                return "☹ Please specify the deadline!";
            }

            String taskDescription = taskDetails.split("/by")[0].trim();
            String deadlineString = taskDetails.split("/by")[1].trim();
            LocalDateTime deadlineTime = null;
            try {
                deadlineTime = LocalDateTime.parse(deadlineString, DATETIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Uhoh! Please use the following format for time dd.mm.yyyy hh:mm");
                return "An error occurred, nothing was added";
            }

            items.add(
                new DeadlineTask(
                    taskDescription, 
                    deadlineTime));
        } else if (taskType.equalsIgnoreCase("event")) {
            if (taskDetails.split("/from").length < 2) {
                return "☹ Please specify the start and end time!";
            }

            String taskDescription = taskDetails.split("/from")[0].trim();
            String periodString = taskDetails.split("/from")[1].trim();
            if (periodString.split("/to").length < 2) {
                return "☹ Please specify the start and end time!";
            }
            LocalDateTime startTime = null;
            LocalDateTime endTime = null;
            try {
                startTime = LocalDateTime.parse(
                        periodString.split("/to")[0].trim(), DATETIME_FORMATTER);
                endTime = LocalDateTime.parse(
                        periodString.split("/to")[1].trim(), DATETIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Uhoh! Please use the following format for time dd.mm.yyyy hh:mm");
                return "An error occurred, nothing was added";
            }

            items.add(
                new EventTask(
                    taskDescription,
                    startTime, 
                    endTime));
        } else if (taskType.equalsIgnoreCase("todo")) {
            items.add(new ToDoTask(taskDetails));
        } else {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        saveList();
        return String.format(
            "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
            items.get( items.size() - 1).toString(),
            items.size());
=======
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    } 

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    } 

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.tasks.size();
>>>>>>> 9e19dbe (Refactor using OOP)
    }

    public void insertTask(Task task) {
        this.tasks.add(task);
    }

    public Task delTask(int taskID) throws ArrayIndexOutOfBoundsException {
        return this.tasks.remove(taskID);
    }

    public Task markTask(int taskID) throws ArrayIndexOutOfBoundsException {
        tasks.get(taskID).markTask();
        return tasks.get(taskID);
    }

    public Task unmarkTask(int taskID) throws ArrayIndexOutOfBoundsException {
        tasks.get(taskID).unmarkTask();
        return tasks.get(taskID);
    }

    /**
     * Print the output in customised format.
     * 
     * @param list The list to print
     * @return String representation of the todo-list
     */
    @Override
    public String toString() {
        String res = ("Here are the tasks in your list:\n");
        for (int i = 0; i <  tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            res += String.format("\n %d.%s", i + 1, tasks.get(i).toString());
        }
        return res;
    }

}
