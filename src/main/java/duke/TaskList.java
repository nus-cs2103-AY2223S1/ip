package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskList {
    private LinkedList<Task> tasks = new LinkedList<>();

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
     * @throws DukeException if index is not an integer in the range of the list length
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
        String csv = "";
        for (int i = 0; i < tasks.size(); i++) {
            csv += tasks.get(i).toCsv();
        }
        return csv;
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
        String match = "";
        for (int i = 0, j = 0; j < tasks.size(); j++) {
            Task curr = tasks.get(j);
            if (curr.contains(keyword)) {
                i++;
                match += i + ". " + curr + "\n";
            }
        }
        if (match == "") {
            System.out.println("Duke: Sorry! Cannot find any matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:\n" + match);
        }
    }

    /**
     * Returns a string representation of this TaskList.
     *
     * @return a string representation of this TaskList
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            str += index + ". " + tasks.get(i).toString() + "\n";
        }
        return str;
    }
}
