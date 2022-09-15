package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import duke.task.Task;


/**
 * Stores taskList and the list of addCommands to be written into output file.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<String> addCommands; // running these commands will always give the tasks array

    /**
     * Constructor for TaskList that initialises an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.addCommands = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> tasks, ArrayList<String> addCommands) {
        this.tasks = tasks;
        this.addCommands = addCommands;
    }

    /**
     * Adds a task to the task list, and writes the change to the output file.
     *
     * @param task Task to be added.
     * @param command input string from user, that returns the Task's AddCommand when passed into Parser.parse().
     * @param storage Storage to write the changes after adding.
     * @throws IOException if an error occurs while writing to the output file.
     */
    public void add(Task task, String command, Storage storage) throws IOException {
        this.tasks.add(task);
        this.addCommands.add(command);

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.tasks.remove(task);
            this.addCommands.remove(command);
            throw new IOException("There was a problem writing the change to the file. duke.task.Task not added.");
        }
    }

    /**
     * Returns the Task instance at index i.
     *
     * @param i 0-based index of Task.
     * @return Task at index i.
     * @throws IndexOutOfBoundsException if i < 0 or i >= size of TaskList.
     */
    public Task getTask(int i) throws IndexOutOfBoundsException {
        return this.tasks.get(i);
    }

    /**
     * Returns the length of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes a task from the task list, and writes the change to the output file.
     *
     * @param i 0-based index of task to be removed.
     * @param storage Storage to write the changes after removing.
     * @throws IOException if an error occurs while writing to the output file.
     */
    public void remove(int i, Storage storage) throws IOException {
        Task task = this.getTask(i);
        String commandString = this.addCommands.get(i);
        this.tasks.remove(i);
        this.addCommands.remove(i);

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.tasks.add(i, task);
            this.addCommands.add(i, commandString);
            throw new IOException("There was a problem writing the change to the file. duke.task.Task not removed.");
        }
    }

    /**
     * Marks a task from the task list, and writes the change to the output file.
     *
     * @param i 0-based index of task to be marked.
     * @param storage Storage to write the changes after marked.
     * @throws IOException if an error occurs while writing to the output file.
     */
    public void mark(int i, Storage storage) throws IOException {
        this.getTask(i).mark();
        String commandString = this.addCommands.get(i);
        if (!commandString.contains("/done")) {
            this.addCommands.set(i, commandString + " /done");
        }

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.getTask(i).unmark();
            this.addCommands.set(i, commandString);
            throw new IOException("There was a problem writing the change to the file. duke.task.Task not marked.");
        }
    }

    /**
     * Unmarks a task from the task list, and writes the change to the output file.
     *
     * @param i 0-based index of task to be unmarked.
     * @param storage Storage to write the changes after unmarked.
     * @throws IOException if an error occurs while writing to the output file.
     */
    public void unmark(int i, Storage storage) throws IOException {
        this.getTask(i).unmark();
        String commandString = this.addCommands.get(i);
        if (commandString.contains("/done")) {
            this.addCommands.set(i, commandString.replace("/done", ""));
        }

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        this.addCommands.stream().forEach(c -> sj.add(c));
        try {
            storage.write(sj.toString());
        } catch (IOException e) {
            this.getTask(i).mark();
            this.addCommands.set(i, commandString);
            throw new IOException("There was a problem writing the change to the file. duke.task.Task not unmarked.");
        }
    }

    /**
     * Searches for tasks that have descriptions containing the keyword.
     *
     * @param keyword String to be searched.
     * @return TaskList containing the matching tasks.
     */
    public TaskList search(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        ArrayList<String> matchingAddCommands = new ArrayList<>();

        IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).textContains(keyword))
                .forEach(i -> {
                    matchingTasks.add(tasks.get(i));
                    matchingAddCommands.add(addCommands.get(i));
                });
        return new TaskList(matchingTasks, matchingAddCommands);
    }

    @Override
    public String toString() {
        return String.join("\n",
                IntStream.range(1, this.size() + 1)
                    .mapToObj(i -> String.format("%d. %s", i, this.getTask(i - 1).toString()))
                    .toArray(String[]::new));
    }
}
