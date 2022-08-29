package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Implements TaskList for Duke.
 */
public class TaskList {
    protected final ArrayList<Task> taskList;
    private final Storage store;
    private final Ui ui;

    public TaskList(Ui uiInstance) {
        taskList = new ArrayList<>();
        store = new Storage();
        ui = uiInstance;
    }

    public void addTask(Task task) {
        taskList.add(task);
        ui.printMessage("Got it. I've added this task:\n      " +
                task +
                "\n    Now you have " +
                taskList.size() +
                " tasks in the list.");
    }

    public void deleteTask(int id) {
        Task t = taskList.remove(id);
        ui.printMessage("Noted. I've removed this task:\n      " +
                t +
                "\n    Now you have " +
                taskList.size() +
                " tasks in the list.");
    }

    public void markTask(int id) {
        Task t = taskList.get(id);
        t.done();
        ui.printMessage("Nice! I've marked this task as done:\n    " + t);
    }

    public void unmarkTask(int id) {
        Task t = taskList.get(id);
        t.undone();
        ui.printMessage("OK, I've marked this task as not done yet:\n    " + t);
    }

    public int getSize() {
        return taskList.size();
    }

    public void generateList() {
        String messageList = "";
        int count = 1;
        for (Task t: taskList) {
            messageList += "\n    " + count++ + ". " + t;
        }
        ui.printMessage("Here are the tasks in your list:\n    " + messageList);
    }

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

    public void saveTasks() {
        for (Task t : taskList) {
            store.writeText(t.toStorageString(), true);
        }
        ui.printMessage("Bye. Hope to see you again soon!");
        store.closeWriter();
    }
}
