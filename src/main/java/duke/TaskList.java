package duke;

import java.util.ArrayList;

/**
 * Contains the task list & list operations.
 */
public class TaskList {

    private ArrayList<Task> log = new ArrayList<>();
    private ArrayList<Task> findLog = new ArrayList<>();
    private boolean wasFinding = false;

    /**
     * Constructs a TaskList.
     */
    TaskList() {
    }

    /**
     * Replaces the contained log with the given ArrayList.
     *
     * @param log ArrayList that replaces the current log.
     */
    public void setLog(ArrayList<Task> log) {
        this.log = log;
    }

    /**
     * Getter for the contained task list.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getLog() {
        return log;
    }

    /**
     * Adds a To-Do to the task list.
     *
     * @param s Data for the To-Do.
     * @throws DukeTaskException  If To-Do can't be created with the input.
     */
    public Task addTodo(String s) throws DukeTaskException {
        wasFinding = false;
        Task temp = new Todo(s, false);
        log.add(temp);
        return temp;
    }

    /**
     * Adds a Deadline to the task list.
     *
     * @param s Data for the Deadline.
     * @throws DukeTaskException  If Deadline can't be created with the input.
     */
    public Task addDeadline(String s) throws DukeTaskException {
        wasFinding = false;
        String by = dateFinder(s, "/by");
        String name = nameFinder(s, "/by");
        Task temp = new Deadline(name, false, by);
        log.add(temp);
        return temp;
    }

    /**
     * Adds an Event to the task list.
     *
     * @param s Data for the Event.
     * @throws DukeTaskException  If Event can't be created with the input.
     */
    public Task addEvent(String s) throws DukeTaskException {
        wasFinding = false;
        String at = dateFinder(s, "/at");
        String name = nameFinder(s, "/at");
        Task temp = new Event(name, false, at);
        log.add(temp);
        return temp;
    }

    /**
     * Prints out the contents of the task list.
     */
    public void list() {
        wasFinding = false;
        printList();
    }

    private void printList() {
        if (findCurrentList().isEmpty()) {
            System.out.println("There were no matching results :(");
            wasFinding = false;
        } else {
            int count = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task item : findCurrentList()) {
                System.out.println(count + ". " + item.toString());
                count++;
            }
        }
    }

    /**
     * Marks and returns task marked.
     */
    public Task mark(int n) {
        Task temp = findCurrentList().get(n);
        temp.mark();
        return temp;
    }

    /**
     * Unmarks and returns task unmarked.
     */
    public Task unmark(int n) {
        Task temp = findCurrentList().get(n);
        temp.unmark();
        return temp;
    }

    private ArrayList<Task> findCurrentList() {
        if (wasFinding) {
            return findLog;
        } else {
            return log;
        }
    }

    /**
     * Deletes task in the given position.
     *
     * @param n Position (integer) of task to be deleted.
     */
    public Task delete(int n) {
        Task temp = findCurrentList().get(n);
        findCurrentList().remove(n);
        if (wasFinding) {
            int count = 0;
            while (!log.get(count).equals(temp)) {
                count++;
            }
            log.remove(count);
        }
        return temp;
    }

    public void findList(String s) {
        wasFinding = true;
        findLog.clear();
        for (Task task : log) {
            if (task.toString().contains(s)) {
                findLog.add(task);
            }
        }
        printList();
    }

    private String dateFinder(String restWord, String flag) {
        return restWord.substring(restWord.indexOf(flag) + flag.length()).trim();
    }

    private String nameFinder(String restWord, String flag) {
        return restWord.substring(0, restWord.indexOf(flag)).trim();
    }
}
