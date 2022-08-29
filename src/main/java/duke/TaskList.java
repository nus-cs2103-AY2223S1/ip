package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Class that stores and manipulates tasks for Duke Bot.
 */
public class TaskList {
    protected final ArrayList<Task> taskList;
    private final Storage store;
    private final Ui ui;

    /**
     * Class constructor for TaskList.
     * 
     * @param uiInstance Ui instance being used in Duke Bot.
     */
    public TaskList(Ui uiInstance) {
        taskList = new ArrayList<>();
        store = new Storage();
        ui = uiInstance;
    }

    /**
     * Adds a task to TaskList.
     * 
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        taskList.add(t);
        ui.printMessage("Got it. I've added this task:\n      " +
                t +
                "\n    Now you have " +
                taskList.size() +
                " tasks in the list.");
    }

    /**
     * Deletes a task from TaskList.
     * 
     * @param id Index of task to be deleted. Index is numbering from calling "list" command.
     */
    public void deleteTask(int id) {
        Task t = taskList.remove(id);
        ui.printMessage("Noted. I've removed this task:\n      " +
                t +
                "\n    Now you have " +
                taskList.size() +
                " tasks in the list.");
    }

    /**
     * Marks a task from TaskList as done.
     * 
     * @param id Index of task to be marked. Index is numbering from calling "list" command.
     */
    public void markTask(int id) {
        Task t = taskList.get(id);
        t.done();
        ui.printMessage("Nice! I've marked this task as done:\n    " + t);
    }

    /**
     * Marks a task from TaskList as undone.
     * 
     * @param id Index of task to be unmarked. Index is numbering from calling "list" command.
     */
    public void unmarkTask(int id) {
        Task t = taskList.get(id);
        t.undone();
        ui.printMessage("OK, I've marked this task as not done yet:\n    " + t);
    }

    /**
     * Returns number of tasks in TaskList
     * 
     * @return Size of TaskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Prints list representation of TaskList.
     * Ui instance is used to print this list.
     */
    public void generateList() {
        String messageList = "";
        int count = 1;
        for (Task t: taskList) {
            messageList += "\n    " + count++ + ". " + t;
        }
        ui.printMessage("Here are the tasks in your list:\n    " + messageList);
    }

    /**
     * Loads tasks from previous instance of Duke Bot.
     * Previous tasks are obtained from an instance of Storage.
     * These previous tasks are presented as Duke Bot commands and are loaded back into TaskList by parsing them.
     * 
     * @param parser Parser being used for Duke Bot.
     */
    public void loadTasks(Parser parser) {
        String tasks = store.getPreviousText();
        if (tasks != null && tasks.length() <= 0) {
            return ;
        }
        for (String s: tasks.split("\n")) {
            try {
                parser.parse(s, false);
            } catch (DukeException e) {
                System.out.println("Failed to load saved tasks: " + e);
            }
        }
    }

    /**
     * Saves task from current TaskList.
     * Tasks are saved using an instance of Storage.
     */
    public void saveTasks() {
        for (Task t : taskList) {
            store.writeText(t.toStorageString(), true);
        }
        ui.printMessage("Bye. Hope to see you again soon!");
        store.closeWriter();
    }
}
