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

    private final String toStage;

    public TaskList() {
        this.tasks = new ArrayList<>(); //should be list as compile time type
        this.tasks.add(new Task("", "", ""));
        this.curr = 0;
        this.toStage = "";
    }

    public TaskList(int curr) {
        this.tasks = new ArrayList<>(); //should be list as compile time type
        this.curr = curr;
        this.toStage = "";
    }

    /**
     * @param tasks arraylist of tasks
     * @param curr  no of tasks
     */
    public TaskList(List<Task> tasks, int curr, String toStage) {
        this.tasks = tasks;
        this.curr = curr;
        this.toStage = toStage;
    }

    public TaskList(TaskList tasklist, String toStage) {
        this.tasks = tasklist.getTasks();
        this.curr = tasklist.getCurr();
        this.toStage = toStage;
    }

    /**
     * programme prints out all the tasks currently at hand
     *
     * @param ui User Interface object
     * @return this task list
     */
    public TaskList listTasks(Ui ui) {
        if (this.curr == 0) {
            return new TaskList(this.tasks, this.curr, ui.emptyListPrint());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are you tasks: \n");
        for (int i = 0; i < curr; i++) {
            Task task = this.getTasks().get(i);
            sb.append(task);
            sb.append("\n");
        }
        return new TaskList(this.tasks, this.curr, sb.toString());
    }

    /**
     * programme marks the specified task as done
     *
     * @param atMark Command string read from UI
     * @param ui     User Interface
     * @return this task list
     */
    public TaskList markTask(String[] atMark, Ui ui) {
        assert (checkInt(atMark[1]));
        int index = Integer.parseInt(atMark[1]);
        tasks.set(index - 1, tasks.get(index - 1).markDone());
        String output = ui.markTaskPrint(tasks.get(index - 1));
        return new TaskList(this.tasks, this.curr, output);
    }

    /**
     * programme marks the specified task as undone
     *
     * @param atUnmark Command string read from UI
     * @param ui       User Interface
     * @return this task list
     */
    public TaskList unmarkTask(String[] atUnmark, Ui ui) {
        assert (checkInt(atUnmark[1]));
        int index = Integer.parseInt(atUnmark[1]);
        tasks.set(index - 1, tasks.get(index - 1).markUndone());
        String output = ui.unmarkTaskPrint(tasks.get(index - 1));
        return new TaskList(this.tasks, this.curr, output);
    }

    /**
     * programme takes in tasks to be deleted at specified index
     * removes the task and returns a new TaskList object
     *
     * @param atDel Commmand string from UI
     * @param ui    User Interface
     * @return new TaskList object, with specified task removed
     */
    public TaskList deleteTask(String[] atDel, Ui ui) {
        assert (checkInt(atDel[1]));
        int index = Integer.parseInt(atDel[1]);
        index--;
        Task del = tasks.get(index);
        for (int i = index; i < curr; i++) {
            tasks.set(i, tasks.get(i + 1));
        }
        String output = ui.deletePrint(del, curr - 1);
        return (this.removeCurr(output));
    }

    /**
     * programme takes in ToDo tasks to be added and
     * adds the task and returns a new TaskList object
     *
     * @param command Commmand string from UI
     * @param ui      User Interface
     * @return new TaskList object, with specified task added
     */
    public TaskList toDoTask(String command, Ui ui) {
        String todo = command.substring(5);
        Task toDoTask = new ToDo(todo);
        tasks.add(curr, toDoTask);
        String output = ui.addTaskPrint(toDoTask, curr + 1);
        return (this.addCurr(output));
    }

    /**
     * returns a new deadline TaskList object, with the specified
     * deadline task added
     *
     * @param command Command string from UI
     * @param ui      User Interface
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
        String output = ui.addTaskPrint(deadlineTask, curr + 1);
        return (this.addCurr(output));
    }

    /**
     * returns a new event TaskList object, with the specified
     * deadline task added
     *
     * @param command Command string from UI
     * @param ui      User Interface
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
        String output = ui.addTaskPrint(eventTask, curr + 1);
        return (this.addCurr(output));
    }

    /**
     * prints out list of task happening at specified date
     * deadline task added
     *
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < curr; i++) {
            if (tasks.get(i).sameDay(dateTime)) {
                sb.append(tasks.get(i));
            }
        }
        return new TaskList(this.tasks, this.curr, sb.toString());
    }

    public TaskList updateTime(String command, Ui ui) {
        String event = command.substring(11);
        System.out.println(event);
        String[] atUpdateTime = event.split(" /to ");
        int index = Integer.parseInt(atUpdateTime[0]) - 1;
        String timing = atUpdateTime[1];
        Task task = this.getTasks().get(index);
        String taskType = task.getType();
        switch (taskType) {
            case "D":
                task = new Deadline(task.getVal(), task.getDone(), timing);
                break;
            case "E":
                task = new Event(task.getVal(), task.getDone(), timing);
                break;
        }
        this.getTasks().set(index, task);
        String output = ui.updatePrint(task);
        return new TaskList(this.getTasks(), this.getCurr(), output);
    }

    public TaskList updateTask(String command, Ui ui) {
        String event = command.substring(11);
        System.out.println(event);
        String[] atUpdateTask = event.split(" /to ");
        int index = Integer.parseInt(atUpdateTask[0]) - 1;
        String taskInstruction = atUpdateTask[1];
        Task task = this.getTasks().get(index);
        String taskType = task.getType();
        switch (taskType) {
            case "D":
                task = new Deadline(taskInstruction, task.getDone(), task.getDateTimeObject());
                break;
            case "E":
                task = new Event(taskInstruction, task.getDone(), task.getDateTimeObject());
                break;
            case "T":
                task = new ToDo(taskInstruction, task.getDone(), "");
                break;
        }
        this.getTasks().set(index, task);
        String output = ui.updatePrint(task);
        return new TaskList(this.getTasks(), this.getCurr(), output);
    }

    public TaskList addCurr(String output) {
        return new TaskList(this.tasks, this.curr + 1, output);
    }

    public TaskList removeCurr(String output) {
        return new TaskList(this.tasks, this.curr - 1, output);
    }

    /**
     * @return the arrayList containing the tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * @return the current no of tasks in list
     */
    public int getCurr() {
        return this.curr;
    }

    /**
     * @return the values to be printed
     */
    public String getToStage() {
        return this.toStage;
    }

    public TaskList findTasks(String command, Ui ui) {
        String subWord = command.substring(5);
        String output;
        List<Task> foundTasks = new ArrayList<Task>();
        boolean hasTasks = false;
        for (int i = 0; i < curr; i++) {
            Task task = tasks.get(i);
            if (task.getVal().contains(subWord)) {
                hasTasks = true;
                foundTasks.add(task);
            }
        }
        if (!hasTasks) {
            output = ui.findNothingPrint();
        } else {
            output = ui.findPrint(foundTasks);
        }
        return new TaskList(this.tasks, this.curr, output);
    }

    private boolean checkInt(String str) {
        return Integer.parseInt(str) == (int) Integer.parseInt(str);
    }

}

