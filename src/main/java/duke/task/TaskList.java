package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import duke.DukeException;
import duke.Ui;

/**
 * Represents a list of <code>Task</code> objects.
 */
public class TaskList {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Constructs a <code>TaskList</code> containing <code>Task</code> from the data file.
     *
     * @param dataFile file with stored tasks
     */
    public TaskList(File dataFile) {
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String[] splitInput = scanner.nextLine().split(",");
                String taskType = splitInput[0];
                String taskDescription = splitInput[2];
                boolean isDone = splitInput[1].equals("1");
                switch (taskType) {
                case "T":
                    Task todo = new Todo(taskDescription);
                    todo.setDone(isDone);
                    tasks.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(taskDescription, splitInput[3]);
                    deadline.setDone(isDone);
                    tasks.add(deadline);
                    break;
                case "E":
                    Task event = new Event(taskDescription, splitInput[3]);
                    event.setDone(isDone);
                    tasks.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds new tasks to the end of this TaskList.
     *
     * @param taskType type of <code>Task</code> to be added
     * @param taskDescription description of <code>Task</code> to be added
     * @param date date property of <code>Task</code> of to be added
     */
    public String editTaskList(String taskType, String taskDescription, String date) {
        Task newTask = null;
        switch (taskType) {
        case "todo":
            newTask = new Todo(taskDescription);
            break;
        case "deadline":
            newTask = new Deadline(taskDescription, date);
            break;
        case "event":
            newTask = new Event(taskDescription, date);
            break;
        }
        tasks.add(newTask);
        return Ui.printTaskCreationMessage(newTask, tasks.size());
    }

    /**
     * Modifies this <code>TaskList</code> by editing the task at the specified position.
     *
     * @param cmd command to be executed
     * @param index index of the <code>Task</code> to be edited
     * @throws DukeException if the index is out of range
     */
    public String editTaskList(String cmd, int index) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Duke: Looks like your task list currently does not have a task at this index.");
        }
        Task t = tasks.get(index);
        String editMessage = "";
        switch (cmd) {
        case "mark":
            editMessage = t.markAsDone();
            break;
        case "unmark":
            editMessage = t.markAsNotDone();
            break;
        case "delete":
            tasks.remove(index);
            editMessage = "Noted. I've removed this task:\n "+ t.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list";
            break;
        }
        System.out.println(editMessage);
        return editMessage;
    }

    /**
     * Returns this <code>TaskList</code> in a CSV format.
     *
     * @return CSV representation of this <code>TaskList</code>
     */
    public String toCsv() {
        StringBuilder csv = new StringBuilder();
        for (Task task : tasks) {
            csv.append(task.toCsv());
        }
        return csv.toString();
    }

    /**
     * Returns true if, and only if, this <code>TaskList</code> has zero tasks.
     *
     * @return true if number of tasks in <code>TaskList</code> is 0, otherwise false
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Prints the list of <code>Task</code> in this <code>TaskList</code> with the keyword in it.
     *
     * @param keyword word being searched for in the <code>TaskList</code>
     */
    public String find(String keyword) {
        StringBuilder match = new StringBuilder();
        int i = 0;
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                i++;
                match.append(i).append(". ").append(task).append("\n");
            }
        }
        return Ui.printTaskSearch(match.toString());
    }

    /**
     * Returns a string representation of this <code>TaskList</code>.
     *
     * @return a string representation of this <code>TaskList</code>
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            str.append(index).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return str.toString();
    }
}
