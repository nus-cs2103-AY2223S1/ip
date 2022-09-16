package duke.task;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.exception.DukeException;

/**
 * A task list is used to store tasks.
 */
public class TaskList {
    private LinkedList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new LinkedList<>();
    }

    /**
     * Constructs a task list from a linked list of tasks.
     *
     * @param tasks A linked list of task to create a task list from.
     */
    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a task list from a saved file.
     *
     * @param savedTasks The file the tasks are to be loaded from.
     */
    public TaskList(File savedTasks) {
        tasks = new LinkedList<>();
        // list of description of error messages generated for unparsable tasks
        LinkedList<String> unparsableTasks = new LinkedList<>();
        try {
            Scanner sc = new Scanner(savedTasks);
            while (sc.hasNextLine()) {
                String ln = sc.nextLine();
                try {
                    // try to parse the saved task
                    char rawTaskType = ln.charAt(0);
                    TaskType savedTaskType = TaskType.readSavedTaskType(rawTaskType);
                    tasks.add(savedTaskType.parseSavedFormat(ln));
                } catch (DukeException e) {
                    // could not parse this task, ignore it and raise it as an error later
                    unparsableTasks.add(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("You have no saved tasks.");
        }

        if (!unparsableTasks.isEmpty()) {
            String unparsableTasksList = String.join("\n", unparsableTasks);
            String errorLog = String.format("Could not parse saved tasks:\n %s", unparsableTasksList);
            System.out.println(errorLog);
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the display message of the number of tasks in the task list.
     *
     * @return The display String representation of the number of tasks in the task list.
     */
    public String numberOfTasks() {
        int numTasks = size();
        if (numTasks == 0) {
            return "your task list looks empty, add some tasks to get started!（･∀･)つ";
        } else {
            // TODO pluralise properly
            int numUncompletedTasks = filter((Task t) -> !t.isDone).size();
            if (numUncompletedTasks == 0) {
                return "yay, you checked everything off your list! ヽ(˘◡˘)ノ";
            } else {
                return String.format("you have %d uncompleted tasks- it's time to get to work! *（･v･)つ *",
                        numUncompletedTasks);
            }
        }
    }

    /**
     * Checks that the specified task is a task that exists.
     *
     * @param i The task number of the task to be verified.
     * @return True if the task exists.
     * @throws DukeException If the task number does not exist.
     */
    public boolean isValidTask(int i) throws DukeException {
        boolean isValid = i > 0 && i <= tasks.size();
        if (!isValid) {
            throw new DukeException("uhoh... bobo can't find this task (・へ・)??");
        }
        return true;
    }

    /**
     * Marks the specified task number as done, if it exists.
     *
     * @param i The task number to be marked as done.
     */
    public String markTaskDone(int i) throws DukeException {
        isValidTask(i);
        assert i > 0 : "Task number should be more than 0";
        assert i <= size() : "Task should exist";
        Task task = tasks.get(i - 1);
        task.markTaskAsDone();
        assert task.isCompleted() : "Task should be marked as done";
        return "awesome!! bobo marked this task as done ~(˘▾˘~)";
    }

    /**
     * Marks the specified task number as not done, if it exists.
     *
     * @param i The task number to be marked as not done.
     * @throws DukeException An exception is thrown when the specified task does not exist.
     */
    public String markTaskNotDone(int i) throws DukeException {
        isValidTask(i);
        assert i > 0 : "Task number should be more than 0";
        assert i <= size() : "Task should exist";
        Task task = tasks.get(i - 1);
        task.markTaskAsUndone();
        return "alright-y, bobo marked this task as not done yet  (・◡・)ゝ";
    }

    /**
     * Adds a specified task to the task list.
     *
     * @param task The task to be added to the task list.
     * @return String representation of task completion, displays task added and number
     *         of tasks in the task list.
     */
    public String addTask(Task task) {
        tasks.add(task);
        String taskAddedSuccessfullyMessage = String.format("aye aye! bobo added this task ( ･o･)"
                + " you now have %d items in your list!", tasks.size());
        return taskAddedSuccessfullyMessage;
    }

    /**
     * Deletes a specified task from the task list.
     *
     * @param i The task number of the task to be deleted from the task list.
     * @return String representation of the task deletion, displays task removed and
     *         the number of remaining tasks in the task list.
     * @throws DukeException Exception thrown when the specified task does not exist.
     */
    public String deleteTask(int i) throws DukeException {
        isValidTask(i);
        assert i > 0 : "Task number should be more than 0";
        assert i <= size() : "Task should exist";
        tasks.remove(i - 1);
        String taskDeletedSuccessfully = String.format("okayy! bobo removed this task (´･ω･`)┐"
                + " you now have %d tasks in your list", tasks.size());
        return taskDeletedSuccessfully;
    }

    /**
     * Filters the task list and returns a new task list containing only elements
     * that fulfil the specified condition.
     *
     * @param pred Condition to test the task elements against.
     * @return A new task list containing only elements that fulfil the specified condition.
     */
    public TaskList filter(Predicate<Task> pred) {
        TaskList filtered = new TaskList();
        for (Task task : tasks) {
            if (pred.test(task)) {
                filtered.addTask(task);
            }
        }
        return filtered;
    }

    /**
     * Transforms the task list into a linked list containing elements of type T
     * by applying the given unary function to every task in the task list.
     *
     * @param func The unary function to apply to every task in the task list.
     * @param <T> The type of object the unary function transforms each task into.
     * @return A linked list of type T obtained by applying the specified function to
     *         each task of the task list.
     */
    public <T> LinkedList<T> transform(Function<Task, T> func) {
        return tasks.stream().map(func).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Transforms the task list into a linked list containing elements of type T
     * by applying the given bi-function to every task and its corresponding
     * task number in the task list.
     *
     * @param func The bi-function to apply to every task in the task list.
     * @param <T> The type of object the bi-function transforms each task and its task number into.
     * @return A linked list of type T obtained by applying the specified function to
     *         each task, and its corresponding task number, of the task list.
     */
    public <T> LinkedList<T> transform(BiFunction<Task, Integer, T> func) {
        LinkedList<T> transformed = new LinkedList<>();
        int taskNumber = 1;
        for (Task task : tasks) {
            transformed.add(func.apply(task, taskNumber));
            taskNumber++;
        }
        return transformed;
    }

    /**
     * Returns a new task list from sorting this task list with the given Comparator.
     * The current task list will not be modified.
     *
     * @param taskListComparator The Comparator to sort the task list with.
     * @return A copy of the task list, with tasks sorted using the Comparator.
     */
    public TaskList sort(Comparator<Task> taskListComparator) {
        LinkedList<Task> tasksCopy = (LinkedList<Task>) tasks.clone();
        Collections.sort(tasksCopy, taskListComparator);
        return new TaskList(tasksCopy);
    }

    /**
     * Returns the task in the task list corresponding to the specified task number.
     *
     * @param taskNumber The task number of the task to be retrieved from the task list.
     * @return The task in the task list corresponding to the specified task number.
     * @throws DukeException If the specified task number is invalid.
     */
    public Task getTask(int taskNumber) throws DukeException {
        isValidTask(taskNumber);
        return tasks.get(taskNumber - 1);
    }

    /**
     * Parses the task list into a string format ready to be saved to the hard disk.
     *
     * @return A savable string representation of the task list.
     */
    public String toSaveFormat() {
        String formatted = "";
        for (Task task : tasks) {
            formatted += task.toSaveFormat() + "\n";
        }
        return formatted;
    }

    /**
     * Stringifies the task list without printing any information.
     *
     * @return The string representation of the task list.
     */
    public String stringify() {
        String taskList = "";
        int count = 0;
        for (Task task : tasks) {
            count++;
            taskList += String.format("\n%d. %s", count, task);
        }
        return (count == 0 ? taskList : taskList.substring(1));
    }

    /**
     * Lists all the tasks entered thus far by the user with a description header.
     */
    @Override
    public String toString() {
        String taskList = stringify();
        return (taskList.equals("") ? "No tasks" : "Here are the tasks in your list:\n" + taskList);
    }
}
