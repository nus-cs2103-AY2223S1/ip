package duke.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Stores the current list of tasks.
 * @author Jicson Toh
 */
public class TaskList {
    private static final Ui ui = new Ui();
    private final ArrayList<Task> list;

    /**
     * Creates the TaskList object.
     * @param data file data to update the list.
     */
    public TaskList(File data) {
        this.list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String type = line.substring(0, 1);
                boolean isDone = Integer.parseInt(line.substring(4, 5)) != 0;
                String action = line.substring(8);
                switch (type) {
                case "T":
                    list.add(new TodoTask(action, isDone));
                    break;
                case "D":
                    int i = action.indexOf('|');
                    String date = action.substring(i + 1).strip();
                    list.add(new DeadlineTask(action.substring(0, i).strip(), isDone, date));
                    break;
                case "E":
                    i = action.indexOf('|');
                    date = action.substring(i + 1).strip();
                    list.add(new EventTask(action.substring(0, i).strip(), isDone, date));
                    break;
                default:
                    ui.showError();
                    break;
                }
            }
        } catch (DukeException | FileNotFoundException e) {
            ui.showError(e.getMessage());
        }
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public String getTaskString(int index) {
        assert index < list.size();
        return list.get(index).toString();
    }

    /**
     * Remove task specified by the index.
     *
     * @param index position of task in list.
     */
    public void removeTask(int index) {
        assert index < list.size();
        this.list.remove(index);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int getSize() {
        return this.list.size();
    }

    /**
     * Edits the status of the task in index position.
     * @param index position of the task.
     * @param mark check if mark or unmark.
     */
    public void markTaskStatus(int index, boolean mark) {
        assert index < list.size();
        if (mark) {
            list.get(index).markDone();
        } else {
            list.get(index).markUnDone();
        }
    }

    public Task getTask(int index) {
        assert index < list.size();
        return list.get(index);
    }
}
