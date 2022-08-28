package duke.task;

import duke.util.DateAndTimeParser;
import duke.DukeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList class which represents an array of Task objects.
 *
 * @author Kavan
 */
public class TaskList {
    private final int TASKTYPE = 0;
    private final int ISTASKDONE = 1;
    private final int TASKDESCRIPTION = 2;
    private final int TASKTIME = 3;

    private ArrayList<Task> tasks;
    private BufferedReader br;

    /**
     * Constructor for a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Constructor for a TaskList object loaded from StoredTasks.
     *
     * @param br Saved-data stored as BufferedReader object.
     * @throws DukeException If IOException is caught.
     */
    public TaskList(BufferedReader br) throws DukeException {
        this.br = br;
        this.tasks = load();
    }

    /**
     * Returns the number of Tasks in the array.
     *
     * @return Number of Tasks in the array as int.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns Task object at index i.
     *
     * @param i Index of Task object to be retrieved.
     * @return Task object at index i.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Appends Task object to the end of the TaskList array.
     *
     * @param task Task object to be appended.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes Task object at index i.
     *
     * @param i Index of Task object to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Return TaskList as a String to be saved in a .txt file.
     *
     * @return TaskList as a String.
     */
    public String taskListToSaveString() {
        StringBuilder saveString = new StringBuilder();
        for (Task task : this.tasks) {
            saveString.append(task.storedTaskString()).append("\n");
        }
        return saveString.toString();
    }

    /**
     * Returns TaskList as an ArrayList<Task>.
     *
     * @return TaskList as an ArrayList<Task>.
     * @throws DukeException If IOException is caught.
     */
    private ArrayList<Task> load() throws DukeException {
        ArrayList<Task> storedTasks = new ArrayList<>(100);
        try {
            String line = this.br.readLine();
            while (line != null) {
                String[] taskArr = line.split("\\|");
                switch (taskArr[TASKTYPE]) {
                case "T":
                    Todos todo = new Todos(taskArr[TASKDESCRIPTION]);
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        todo.markAsDone();
                    }
                    storedTasks.add(todo);
                    break;
                case "D":
                    Deadlines deadline = new Deadlines(taskArr[TASKDESCRIPTION], taskArr[TASKTIME],
                            DateAndTimeParser.validateAndParse(taskArr[TASKTIME]));
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        deadline.markAsDone();
                    }
                    storedTasks.add(deadline);
                    break;
                case "E":
                    Events event = new Events(taskArr[TASKDESCRIPTION], taskArr[TASKTIME]);
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        event.markAsDone();
                    }
                    storedTasks.add(event);
                    break;
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Failure in reading file, creating new save file");
        }
        return storedTasks;
    }
}
