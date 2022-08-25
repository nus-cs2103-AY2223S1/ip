package duke.data;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.util.DukeException;
import duke.util.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static final Ui ui = new Ui();
    private ArrayList<Task> list;

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
                }
            }
        } catch (DukeException | FileNotFoundException e) {
            ui.showError();
        }
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public String getTask(int index) {
        return list.get(index).toString();
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int getSize() {
        return this.list.size();
    }

    public void markTaskStatus(int index, boolean mark) {
        if (mark) {
            list.get(index).markDone();
        } else {
            list.get(index).markUnDone();
        }
    }

}
