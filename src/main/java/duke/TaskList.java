package duke;

import java.util.ArrayList;

/**
 * Contains the task list & list operations.
 */
public class TaskList {

    private ArrayList<Task> log;

    /**
     * Constructs a TaskList.
     */
    TaskList() {
        log = new ArrayList<>();
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
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : log) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    /**
     * Marks and returns task marked.
     */
    public Task mark(int n) {
        Task temp = log.get(n);
        temp.mark();
        return temp;
    }

    /**
     * Unmarks and returns task unmarked.
     */
    public Task unmark(int n) {
        Task temp = log.get(n);
        temp.unmark();
        return temp;
    }

    /**
     * Deletes task in the given position.
     *
     * @param n Position (integer) of task to be deleted.
     */
    public Task delete(int n) {
        Task temp = log.get(n);
        log.remove(n);
        return temp;
    }

    private String dateFinder(String restWord, String flag) {
        return restWord.substring(restWord.indexOf(flag) + flag.length()).trim();
    }

    private String nameFinder(String restWord, String flag) {
        return restWord.substring(0, restWord.indexOf(flag)).trim();
    }
}
