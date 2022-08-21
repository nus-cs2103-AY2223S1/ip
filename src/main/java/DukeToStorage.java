import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage all interactions between Duke and UserInputHistory file storage
 */
public class DukeToStorage {
    private ArrayList<Task> userInputHistory = new ArrayList<>();
    private UserInputHistory file = new UserInputHistory();

    /**
     * Called by Duke to start storage.
     * Syncs userInputHistory array with disk storage.
     */
    public DukeToStorage()  {
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
            currTask = TypeConverter.fileLineToTask(linesInFile.get(i));
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
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", t, userInputHistory.size());
            System.out.print(">>");
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
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", d, userInputHistory.size());
            System.out.print(">>");
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
            System.out.printf("Noted down: %s\n There are %d items on your list now. \n", e, userInputHistory.size());
            System.out.print(">>");
        }
    }

    /**
     * Method to show history as stored on disk
     */
    public void showHistory() throws IOException{
        int count = 1;
        System.out.print("______\n");
        System.out.println("Tasks in your list are: ");
        List<String> history = file.getAllLines();
        for (String s: history) {
            System.out.printf("%d. %s\n", count, s);
            count++;
        }
        System.out.printf("Total: %d\n", userInputHistory.size());
        System.out.print("______\n");
        System.out.print(">>");
    }

    /**
     * Return task stored at index n - 1
     * @param n index to asked for by user.
     * @return task at index n - 1 in the userInputHistory arraylist
     */
    public Task getTask(int n)  {
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
            System.out.printf("Task removed: \n%s\n", taskToModify);
            System.out.printf("Total: %d\n", userInputHistory.size());
            System.out.print("______\n");
            System.out.print(">>");
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
            System.out.printf("Marked task %d \n%s\n", n, taskToModify);
            System.out.print(">>");
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
            System.out.printf("Unmarked task %d \n%s\n", n, taskToModify);
            System.out.print(">>");
        }
    }

    public int numberOfTasksInList() {
        return userInputHistory.size();
    }
}
