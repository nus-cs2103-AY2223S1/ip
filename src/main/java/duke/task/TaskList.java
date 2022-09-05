package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import duke.exception.DukeException;
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
                    Task deadline = new Deadline(taskDescription, LocalDate.parse(splitInput[3]));
                    deadline.setDone(isDone);
                    tasks.add(deadline);
                    break;
                case "E":
                    Task event = new Event(taskDescription, LocalDate.parse(splitInput[3]));
                    event.setDone(isDone);
                    tasks.add(event);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String addTask(Task newTask) {
        tasks.add(newTask);
        return Ui.printTaskCreationMessage(newTask, tasks.size());
    }

    /**
     * Deletes <code>Task</code> at the given index from <code>TaskList</code>
     *
     * @param index index of task being deleted
     * @return successful task deletion message
     */
    public String deleteTask(int index) {
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Invalid task index!");
        }
        Task deletedTask = tasks.remove(index);
        return Ui.printTaskDeletionMessage(deletedTask, tasks.size());
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
     * Marks the <code>Task</code> at the given index as done.
     *
     * @param index index of Task to be marked
     * @return successful task marking message
     */
    public String markAsDone(int index) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Duke: Looks like your task list currently does not have a task at this index.");
        }
        Task task = tasks.get(index);
        return task.markAsDone();
    }

    /**
     * Marks the <code>Task</code> at the given index as not done.
     *
     * @param index index of Task to be marked
     * @return successful task marking message
     */
    public String markAsNotDone(int index) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Duke: Looks like your task list currently does not have a task at this index.");
        }
        Task task = tasks.get(index);
        assert task != null : "Task not found";
        return task.markAsNotDone();
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
