package duke.taskList;

import duke.ui.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final int curr;//no of elements in list
    public TaskList() {
        this.tasks = new ArrayList<>(); //should be list as compile time type
        this.tasks.add(new Task("", ""));
        this.curr = 0;
    }

    public TaskList(int curr) {
        this.tasks = new ArrayList<>(); //should be list as compile time type
        this.curr = curr;
    }

    public TaskList(List<Task> tasks, int curr) {
        this.tasks = tasks;
        this.curr = curr;
    }

    public TaskList listTasks(Ui ui) {
        if(this.curr == 0) {
            ui. emptyList();
        }
        for (int i = 0; i < curr; i++) {
            System.out.println(tasks.get(i));
        }
        return this;
    }

    public TaskList markTask(String[] atMark, Ui ui) {
        int index = Integer.parseInt(atMark[1]);
        tasks.set(index - 1, tasks.get(index - 1).markDone());
        ui.markTask(tasks.get(index - 1));
        return this;
    }

    public TaskList unmarkTask(String[] atUnmark, Ui ui) {
        int index = Integer.parseInt(atUnmark[1]);
        tasks.set(index - 1, tasks.get(index - 1).markUndone());
        ui.unmarkTask(tasks.get(index - 1));
        return this;
    }

    public TaskList deleteTask (String[] atDel, Ui ui) {
        int index = Integer.parseInt(atDel[1]);
        index--;
        Task del = tasks.get(index);
        for (int i = index; i < curr; i++) {
            tasks.set(i, tasks.get(i + 1));
        }
        ui.deletePrint(del, curr - 1);
        return(this.removeCurr());
    }

    public TaskList toDoTask(String command, Ui ui) {
        String todo = command.substring(5);
        Task task = new ToDo(todo);
        tasks.add(curr, task);
        ui.addTaskPrint(task, curr + 1);
        return(this.addCurr());
    }

    public TaskList deadlineTask(String command, Ui ui) {
        String deadline = command.substring(9);
        String[] atDead = deadline.split(" /by ");
        String[] timeDead = atDead[1].split("/");
        timeDead[0] = String.format("%02d", Integer.parseInt(timeDead[0]));
        timeDead[1] = String.format("%02d", Integer.parseInt(timeDead[1]));
        String dateDead = String.join("/", timeDead);
        Task task = new Deadline(atDead[0], dateDead);
        tasks.add(curr, task);
        ui.addTaskPrint(task, curr + 1);
        return(this.addCurr());
    }

    public TaskList eventTask(String command, Ui ui) {
        String event = command.substring(6);
        String[] atEvent = event.split(" /by ");
        String[] timeEvent = atEvent[1].split("/");
        timeEvent[0] = String.format("%02d", Integer.parseInt(timeEvent[0]));
        timeEvent[1] = String.format("%02d", Integer.parseInt(timeEvent[1]));
        String dateEvent = String.join("/", timeEvent);
        Task task = new Event(atEvent[0], dateEvent);
        tasks.add(curr, task);
        ui.addTaskPrint(task, curr + 1);
        return(this.addCurr());
    }

    public TaskList thingsTask(String command) {
        String[] at = command.split(" /on ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String[] time = at[1].split("/");
        time[0] = String.format("%02d", Integer.parseInt(time[0]));
        time[1] = String.format("%02d", Integer.parseInt(time[1]));
        String date = String.join("/", time);
        LocalDateTime dateTime = LocalDateTime.parse(date + " 0000", formatter);
        for (int i = 0; i < curr; i++) {
            if (tasks.get(i).sameDay(dateTime)) {
                System.out.println(tasks.get(i));
            }
        }
        return this;
    }

    public TaskList addCurr() {
        return new TaskList(this.tasks, this.curr + 1);
    }

    public TaskList removeCurr() {
        return new TaskList(this.tasks, this.curr - 1);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getCurr() {
        return this.curr;
    }


}

