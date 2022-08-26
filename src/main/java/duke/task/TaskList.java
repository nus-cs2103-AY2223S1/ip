package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import duke.DukeException;
import duke.Ui;

public class TaskList {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Constructs a task list containing tasks from the data file.
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
     * @param taskType type of task to be added
     * @param taskDescription description of task to be added
     * @param date date property of task of to be added
     */
    public void editTaskList(String taskType, String taskDescription, String date) {
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
        Ui.printTaskCreationMessage(newTask, tasks.size());
    }

    /**
     * Modifies this Tasklist by editing the task at the specified position.
     *
     * @param cmd command to be executed
     * @param index index of the task to be edited
     * @throws DukeException if the index is out of range
     */
    public void editTaskList(String cmd, int index) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Duke: Looks like your task list currently does not have a task at this index.");
        }
        Task t = tasks.get(index);
        switch (cmd) {
        case "mark":
            t.markAsDone();
            break;
        case "unmark":
            t.markAsNotDone();
            break;
        case "delete":
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n "+ t.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list");
            break;
        }
    }

    /**
     * Returns this TaskList in a CSV format.
     *
     * @return CSV representation of this TaskList
     */
    public String toCsv() {
        StringBuilder csv = new StringBuilder();
        for (Task task : tasks) {
            csv.append(task.toCsv());
        }
        return csv.toString();
    }

    /**
     * Returns true if, and only if, this TaskList has zero tasks.
     *
     * @return true if number of tasks in TaskList is 0, otherwise false
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Prints the list of tasks in this TaskList with the keyword in it.
     *
     * @param keyword word being searched for in the TaskList
     */
    public void find(String keyword) {
        StringBuilder match = new StringBuilder();
        int i = 0;
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                i++;
                match.append(i).append(". ").append(task).append("\n");
            }
        }
        Ui.printTaskSearch(match.toString());
    }

    /**
     * Returns a string representation of this TaskList.
     *
     * @return a string representation of this TaskList
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
