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
     *
     * @return String containing tasks in task list.
     */
    public String list() {
        wasFinding = false;
        return printList();
    }

    private String printList() {
        if (findCurrentList().isEmpty()) {
            wasFinding = false;
            return ("There were no matching results :(");
        } else {
            StringBuilder text;
            int count = 1;
            text = new StringBuilder(("Here are the tasks in your list: \n"));
            for (Task item : findCurrentList()) {
                text.append(count).append(". ").append(item.toString()).append("\n");
                count++;
            }
            assert count > 1;
            return text.toString();
        }
    }

    /**
     * Marks and returns task marked.
     *
     * @return Task that was marked.
     * @throws DukeException  If index given is out of bounds.
     */
    public Task mark(int n) throws DukeException {
        if (n > findCurrentList().size() || n < 1) {
            throw new DukeException("I can't mark an index beyond the list");
        }
        Task temp = findCurrentList().get(n);
        temp.mark();
        return temp;
    }

    /**
     * Returns size of task list as an integer.
     *
     * @return int Length of task list.
     */
    public int size() {
        return findCurrentList().size();
    }

    /**
     * Unmarks and returns task unmarked.
     *
     * @return Task that was unmarked.
     * @throws DukeException  If index given is out of bounds.
     */
    public Task unmark(int n) throws DukeException {
        if (n > findCurrentList().size() || n < 1) {
            throw new DukeException("I can't unmark an index beyond the list");
        }
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
     * @throws DukeException  If index given is out of bounds.
     */
    public Task delete(int n) throws DukeException {
        if (n > findCurrentList().size() || n < 0) {
            throw new DukeException("I can't delete an index beyond the list");
        }
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

    /**
     * Deletes all tasks.
     */
    public void deleteAll() {
        log.clear();
        findLog.clear();
    }

    /**
     * Lists tasks that matches the key word(s).
     *
     * @param s Key word(s) to search for.
     */
    public String findList(String s) {
        wasFinding = true;
        findLog.clear();
        for (Task task : log) {
            if (task.toString().contains(s)) {
                findLog.add(task);
            }
        }
        return printList();
    }

    private String dateFinder(String restWord, String flag) {
        return restWord.substring(restWord.indexOf(flag) + flag.length()).trim();
    }

    private String nameFinder(String restWord, String flag) {
        return restWord.substring(0, restWord.indexOf(flag)).trim();
    }
}
