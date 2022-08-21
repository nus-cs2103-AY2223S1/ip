import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage all interactions between Duke and UserInputHistory file storage.
 */
public class TaskList {
    private ArrayList<Task> userInputHistory = new ArrayList<>();
    private Storage file = new Storage();

    /**
     * Called by Duke to start storage.
     * Syncs userInputHistory array with disk storage.
     */
    public TaskList()  {
        try {
            syncArrayListWithFile();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e);
        }
    }

    /**
     * Syncs all changes stored in disk to arrayList maintained by program, by:
     * 1. Emptying userInputHistory arraylist,
     * 2. Copying all lines on disk to userInputHistory
     * @throws IOException when fileLineToTask() fails
     */
    private void syncArrayListWithFile() throws IOException, DukeException {
        userInputHistory.removeAll(userInputHistory);
        List<String> linesInFile = file.getAllLines();
        Task currTask;
        int n = linesInFile.size(), i = 0;
        for (; i < n; i ++) {
            currTask = StorageReader.fileLineToTask(linesInFile.get(i));
            userInputHistory.add(currTask);
            System.out.println(currTask);
        }
    }

    /**
     * Adds Task to arrayList and updates file storage.
     * @param t task to be added.
     */
    public void addTaskToHistory(Task t) {
        String str = t.toString() + "\n";
        if (file.appendLine(str)) {
            userInputHistory.add(t);
            Formatter.uiTaskDescription(t.description, numberOfTasksInList());
        }
    }

    /**
     * Adds Deadline to arrayList and updates file storage.
     * @param d deadline to be added.
     */
    public void addDeadlineToHistory(Deadline d) {
        String str = d.toString() + "\n";
        if (file.appendLine(str)) {
            userInputHistory.add(d);
            Formatter.uiTaskDescription(d.description, numberOfTasksInList());
        }
    }

    /**
     * Adds Event to arrayList and updates file storage.
     * @param e event to be added.
     */
    public void addEventToHistory(Event e) {
        String str = e.toString() + "\n";
        if (file.appendLine(str)) {
            userInputHistory.add(e);
            Formatter.uiTaskDescription(e.description, numberOfTasksInList());
        }
    }

    /**
     * Method to show history as stored on disk
     */
    public void showHistory() throws IOException{
        int count = 1;
        StringBuffer listElements = new StringBuffer();
        List<String> history = file.getAllLines();
        for (String s: history) {
            listElements.append(String.format("%d. %s\n", count, s));
            count++;
        }
        Formatter.uiHistory(listElements, numberOfTasksInList());
    }

    /**
     * Return task stored at index n - 1
     * @param n index to asked for by user.
     * @return task at index n - 1 in the userInputHistory arraylist
     */
    private Task getTask(int n)  {
        return userInputHistory.get(n - 1);
    }

    /**
     * Delete task at index n - 1 in userInputHistory and disk
     * @param n index to be deleted
     */
    public void deleteTask(int n)  {
        Task taskToModify = userInputHistory.get(n - 1);
        if (file.deleteLine(n)) {
            userInputHistory.remove(n - 1);
            Formatter.uiDeleteTask(taskToModify, numberOfTasksInList());
        }
    }

    /**
     * Marks task at index n and updates disk with change made.
     * @param n index of task to be marked.
     */
    public void markTask(int n) {
        Task taskToModify = userInputHistory.get(n - 1);
        taskToModify.markAsDone();
        if (file.changeLine(n, taskToModify.toString())) {
            Formatter.uiMarkingTask("Marked", numberOfTasksInList(), taskToModify);
        }
    }

    /**
     * Unmarks task at index n and updates disk with change made.
     * @param n index of task to be unmarked.
     */
    public void unMarkTask(int n) {
        Task taskToModify = userInputHistory.get(n - 1);
        taskToModify.markAsNotDone();
        if (file.changeLine(n, taskToModify.toString())) {
            Formatter.uiMarkingTask("Unmarked", numberOfTasksInList(), taskToModify);
        }
    }

    public int numberOfTasksInList() {
        return userInputHistory.size();
    }

    /**
     * Checks if task is due today.
     * @param n
     */
    public void isToday(int n) {
        Task dt = userInputHistory.get(n - 1);
        String output;
        boolean isToday = dt.isToday();
        if (isToday) {
            output = "Task is due today>>";
        } else {
            output = "Task is not due today>>";
        }
        Formatter.specialOperations(output);
    }

    public void longDescription(int n) {
        Task dt = userInputHistory.get(n - 1);
        Formatter.specialOperations(dt.longDescription());
    }
}
