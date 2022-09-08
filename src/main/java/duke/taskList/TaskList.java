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

/**
 * A taskList class that stores all the tasks currently on hand
 * task stored as ArrayList, with no of tasks as curr
 */
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

    /**
     *
     * @param tasks arraylist of tasks
     * @param curr no of tasks
     */
    public TaskList(List<Task> tasks, int curr) {
        this.tasks = tasks;
        this.curr = curr;
    }

    /**
     * programme prints out all the tasks currently at hand
     * @param ui User Interface object
     * @return this task list
     */
    public TaskList listTasks(Ui ui) {
        if(this.curr == 0) {
            ui. emptyListPrint();
        }
        for (int i = 0; i < curr; i++) {
            System.out.println(tasks.get(i));
        }
        return this;
    }

    /**
     * programme marks the specified task as done
     * @param atMark Command string read from UI
     * @param ui User Interface
     * @return this task list
     */
    public TaskList markTask(String[] atMark, Ui ui) {
        int index = Integer.parseInt(atMark[1]);
        tasks.set(index - 1, tasks.get(index - 1).markDone());
        ui.markTaskPrint(tasks.get(index - 1));
        return this;
    }

    /**
     * programme marks the specified task as undone
     * @param atUnmark Command string read from UI
     * @param ui User Interface
     * @return this task list
     */
    public TaskList unmarkTask(String[] atUnmark, Ui ui) {
        int index = Integer.parseInt(atUnmark[1]);
        tasks.set(index - 1, tasks.get(index - 1).markUndone());
        ui.unmarkTaskPrint(tasks.get(index - 1));
        return this;
    }

    /**
     * programme takes in tasks to be deleted at specified index
     * removes the task and returns a new TaskList object
     * @param atDel Commmand string from UI
     * @param ui User Interface
     * @return new TaskList object, with specified task removed
     */
    public TaskList deleteTask (String[] atDel, Ui ui) {
        int index = Integer.parseInt(atDel[1]);
        index--;
        Task del = tasks.get(index);
        for (int i = index; i < curr; i++) {
            System.out.println("error here");
            tasks.set(i, tasks.get(i + 1));
        }
        ui.deletePrint(del, curr - 1);
        return(this.removeCurr());
    }

    /**
     * programme takes in ToDo tasks to be added and
     * adds the task and returns a new TaskList object
     * @param command Commmand string from UI
     * @param ui User Interface
     * @return new TaskList object, with specified task added
     */
    public TaskList toDoTask(String command, Ui ui) {
        String todo = command.substring(5);
        Task toDoTask = new ToDo(todo);
        tasks.add(curr, toDoTask);
        ui.addTaskPrint(toDoTask, curr + 1);
        return(this.addCurr());
    }

    /**
     * returns a new deadline TaskList object, with the specified
     * deadline task added
     * @param command Command string from UI
     * @param ui User Interface
     * @return new TaskList object, with deadline task added
     */
    public TaskList deadlineTask(String command, Ui ui) {
        String deadline = command.substring(9);
        String[] atDead = deadline.split(" /by ");
        String[] timeDead = atDead[1].split("/");
        timeDead[0] = String.format("%02d", Integer.parseInt(timeDead[0]));
        timeDead[1] = String.format("%02d", Integer.parseInt(timeDead[1]));
        String dateDead = String.join("/", timeDead);
        Task deadlineTask = new Deadline(atDead[0], dateDead);
        tasks.add(curr, deadlineTask);
        ui.addTaskPrint(deadlineTask, curr + 1);
        return(this.addCurr());
    }

    /**
     * returns a new event TaskList object, with the specified
     * deadline task added
     * @param command Command string from UI
     * @param ui User Interface
     * @return new TaskList object, with event task added
     */
    public TaskList eventTask(String command, Ui ui) {
        String event = command.substring(6);
        String[] atEvent = event.split(" /by ");
        String[] timeEvent = atEvent[1].split("/");
        timeEvent[0] = String.format("%02d", Integer.parseInt(timeEvent[0]));
        timeEvent[1] = String.format("%02d", Integer.parseInt(timeEvent[1]));
        String dateEvent = String.join("/", timeEvent);
        Task eventTask = new Event(atEvent[0], dateEvent);
        tasks.add(curr, eventTask);
        ui.addTaskPrint(eventTask, curr + 1);
        return(this.addCurr());
    }

    /**
     * prints out list of task happening at specified date
     * deadline task added
     * @param command Command string from UI
     * @return this task object
     */
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

    /**
     *
     * @return the arrayList containing the tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     *
     * @return the current no of tasks in list
     */
    public int getCurr() {
        return this.curr;
    }

    public TaskList findTasks(String command, Ui ui) {
        String subWord = command.substring(5);
        List<Task> foundTasks= new ArrayList<Task>();
        boolean hasTasks = false;
        for(int i = 0; i < curr; i++) {
            Task task = tasks.get(i);
            if(task.getVal().contains(subWord)) {
                hasTasks = true;
                foundTasks.add(task);
            }
        }
        if(!hasTasks) {
            ui.findNothingPrint();
        } else {
            ui.findPrint(foundTasks);
        }
        return this;
    }

}

