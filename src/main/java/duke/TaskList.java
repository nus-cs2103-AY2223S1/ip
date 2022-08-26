package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    TaskList() {

    }

    TaskList (String data) {
        if (!data.equals("")) {
            String[] tasksArray = data.split("\n");
            for (String task : tasksArray) {
                this.tasks.add(stringToTask(task));
            }
        }
    }

    private static Task stringToTask(String input) {
        String [] taskDetails = input.split(",");
        String taskDescription = taskDetails[2];
        boolean isDone;
        isDone = taskDetails[1].equals("1");
        if (taskDetails.length == 4) {
            String time = taskDetails[3];
            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (taskDetails[0].equals("D")) {
                return new Deadline(taskDescription, isDone, date);
            } else {
                return new Event(taskDescription, isDone, date);
            }
        }
        return new Todo(taskDescription, isDone);
    }

    private static String taskToString(Task task) {
        String taskDescription = task.getDescription();
        String completed = (task.isDone()) ? "1" : "0";
        String type = task.toString().substring(1,2);
        String [] splitTime = task.toString().split(":");
        if (splitTime.length == 2) {
            String time = splitTime[1];
            return String.join(",", type, completed, taskDescription, time.substring(1,time.length()-1));
        }
        return String.join(",", type, completed, taskDescription);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int ind) throws DukeException {
        try {
            Task deletingTask = this.tasks.get(ind);
            this.tasks.remove(ind);
            return deletingTask;
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("Index given is out of range");
        }
    }

    public Task get(int i) throws DukeException {
        try {
            return this.tasks.get(i);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("Index given is out of range");
        }
    }

    protected int size() {
        return this.tasks.size();
    }

    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i != 0) {
                data.append(System.lineSeparator());
            }
            data.append(taskToString(this.tasks.get(i)));
        }
        return data.toString();
    }
}
