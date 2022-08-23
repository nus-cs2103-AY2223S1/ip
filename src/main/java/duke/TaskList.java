package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains the list of tasks for Duke.
 */
public class TaskList {
    private static List<Task> savedTasks;

    public TaskList() {
        TaskList.savedTasks = new ArrayList<Task>();
    }

    public TaskList(String savedTasks) throws DukeException {
        Scanner s = new Scanner(savedTasks);
        TaskList.savedTasks = new ArrayList<Task>();

        while (s.hasNext()) {
            Task newTask = null;

            String[] inputArr = s.nextLine().split(" \\| ");
            String taskType = inputArr[0];

            if (taskType.equals("T")) {
                newTask = new ToDo();
                newTask.addName("todo " + inputArr[2]);
            } else if (taskType.equals("E")) {
                newTask = new Event();
                newTask.addName("event " + inputArr[2] + " /at " + inputArr[3]);
            } else if (taskType.equals("D")) {
                newTask = new DeadLine();
                newTask.addName("deadline " + inputArr[2] + " /by " + inputArr[3]);
            } else {
                throw new DukeException("File is corrupted!");
            }

            if (inputArr[1].equals("1")) {
                newTask.markAsDone();
            }
            TaskList.savedTasks.add(newTask);
        }
    }

    /**
     * Adds a new task into the existing list of tasks.
     *
     * @param task the task which will be added
     */
    public void add(Task task) {
        TaskList.savedTasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task.getStatus() +
                String.format("\nNow you have %d tasks in the list.", TaskList.savedTasks.size()));
    }

    /**
     * Deletes a task from the existing list of tasks.
     * <p>
     * The index provided has to be within the length of the current list.
     *
     * @param index the index of the task which the user wishes to delete
     * @throws DukeException
     */
    public void delete(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (index <= 0 || index > size) {
            throw new DukeException("Index has to be between 1 and " + String.valueOf(size));
        } else {
            Task removedTask = TaskList.savedTasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + removedTask.getStatus()
                    + String.format("\nNow you have %d tasks in the list.", size - 1));
        }
    }

    /**
     * Marks a task from the existing list of tasks.
     * <p>
     * The index provided has to be within the length of the current list.
     *
     * @param index the index of the task to be marked as done
     * @throws DukeException
     */
    public void mark(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (index <= 0 || index > size) {
            throw new DukeException("Index has to be between 1 and " + String.valueOf(size));
        } else {
            Task selectedTask = TaskList.savedTasks.get(index - 1);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + selectedTask.getStatus());
        }
    }

    /**
     * Unmarks a task from the existing list of tasks.
     * <p>
     * The index provided has to be within the length of the current list.
     *
     * @param index the index of the task to be unmarked as done
     * @throws DukeException
     */
    public void unmark(int index) throws DukeException {
        int size = TaskList.savedTasks.size();
        if (index <= 0 || index > size) {
            throw new DukeException("Index has to be between 1 and " + String.valueOf(size));
        } else {
            Task selectedTask = TaskList.savedTasks.get(index - 1);
            selectedTask.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + selectedTask.getStatus());
        }
    }

    /**
     * Returns a string that is formatted to display the index of a task, the type of task, the
     * description of the task and the date of the task.
     *
     * @return a String which shows the current list of tasks
     */
    public String getList() {
        String listString = "";
        for (int i = 0; i < savedTasks.size(); i++) {
            listString += String.format("%d. %s", i + 1, savedTasks.get(i).getStatus());
            if (i != savedTasks.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    /**
     * Returns a string that is formatted to store the index of a task, the type of task, the
     * description of the task and the date of the task for future use.
     *
     * @return a String for storage purpose
     */
    public String writeTasks() {
        String dataWritten = "";

        for (int i = 0; i < TaskList.savedTasks.size(); i++) {
            dataWritten += TaskList.savedTasks.get(i).getTask();

            if (i != TaskList.savedTasks.size() - 1) {
                dataWritten += "\n";
            }
        }

        return dataWritten;
    }



}
