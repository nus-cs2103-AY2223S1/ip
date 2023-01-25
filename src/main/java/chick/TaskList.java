package chick;

import java.util.ArrayList;
import java.util.HashSet;

import chick.task.Task;

/**
 * Class that stores and manipulates tasks for Duke Bot.
 */
public class TaskList {
    protected final ArrayList<Task> tasks;
    private final Storage store;
    private final Ui ui;
    private final HashSet<String> taskSet;

    /**
     * Class constructor for TaskList.
     *
     * @param uiInstance Ui instance being used in Duke Bot.
     */
    public TaskList(Ui uiInstance) {
        tasks = new ArrayList<>();
        store = new Storage();
        ui = uiInstance;
        taskSet = new HashSet<>();
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task to be added.
     * @return Response string from Duke Bot.
     * @throws ChickException If duplicate task is added.
     */
    public String addTask(Task task) throws ChickException {
        if (taskSet.contains(task.toStorageString())) {
            throw new ChickException("no duplicate tasks pls");
        }
        tasks.add(task);
        taskSet.add(task.toStorageString());
        saveTasks();
        return ui.printMessage("Added:\n    "
                + task
                + "\n    Total "
                + tasks.size()
                + " tasks.");
    }

    /**
     * Deletes a task from TaskList.
     *
     * @param id Index of task to be deleted. Index is numbering from calling "list" command.
     * @return Response string from Duke Bot.
     * @throws ChickException If id is out of bounds.
     */
    public String deleteTask(int id) throws ChickException {
        if (id < 0 || id >= tasks.size()) {
            throw new ChickException("proper index pls");
        }
        Task t = tasks.remove(id);
        taskSet.remove(t.toStorageString());
        saveTasks();
        return ui.printMessage("Removed:\n    "
                + t
                + "\n    Total "
                + tasks.size()
                + " tasks.");
    }

    /**
     * Marks a task from TaskList as done.
     *
     * @param id Index of task to be marked. Index is numbering from calling "list" command.
     * @return Response string from Duke Bot.
     * @throws ChickException If id is out of bounds.
     */
    public String markTask(int id) throws ChickException {
        if (id < 0 || id >= tasks.size()) {
            throw new ChickException("proper index pls");
        }
        Task t = tasks.get(id);
        boolean statusChanged = t.setAsDone();
        if (statusChanged) {
            saveTasks();
        }
        return ui.printMessage("Marked:\n    " + t);
    }

    /**
     * Marks a task from TaskList as undone.
     *
     * @param id Index of task to be unmarked. Index is numbering from calling "list" command.
     * @return Response string from Duke Bot.
     * @throws ChickException If id is out of bounds.
     */
    public String unmarkTask(int id) throws ChickException {
        if (id < 0 || id >= tasks.size()) {
            throw new ChickException("proper index pls");
        }
        Task t = tasks.get(id);
        boolean statusChanged = t.setAsUndone();
        if (statusChanged) {
            saveTasks();
        }
        return ui.printMessage("Unmarked:\n    " + t);
    }

    /**
     * Prints list of tasks in TaskList that contains identifier.
     *
     * @param identifier String to search for tasks.
     * @return Response string from Duke Bot.
     */
    public String findTask(String identifier) {
        StringBuilder messageList = new StringBuilder();
        int taskCount = 1;
        for (Task t: tasks) {
            if (t.getDescription().contains(identifier)) {
                messageList.append("\n    ").append(taskCount++).append(". ").append(t);
            }
        }
        return ui.printMessage("Here:" + messageList);
    }

    /**
     * Returns number of tasks in TaskList
     *
     * @return Size of TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints list representation of TaskList.
     * Ui instance is used to print this list.
     *
     * @return Response string from Duke Bot.
     */
    public String generateList() {
        StringBuilder messageList = new StringBuilder();
        int taskCount = 1;
        for (Task t: tasks) {
            messageList.append("\n    ").append(taskCount++).append(". ").append(t);
        }
        return ui.printMessage("Here:" + messageList);
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
        if (tasks == null) {
            return;
        }
        String[] taskSplit = tasks.split("\n");
        for (String s: taskSplit) {
            try {
                parser.parse(s, false);
            } catch (ChickException e) {
                System.out.println("Failed to load saved tasks: " + e);
            }
        }
    }

    /**
     * Saves tasks from current TaskList.
     * Tasks are saved using an instance of Storage.
     */
    public void saveTasks() {
        StringBuilder storageString = new StringBuilder();
        for (Task t : tasks) {
            storageString.append(t.toStorageString());
            storageString.append("\n");
        }
        store.saveText(storageString.toString());
    }
}
