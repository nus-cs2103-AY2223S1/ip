import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {

    private ArrayList<Task> log;

    TaskList() {
        log = new ArrayList<>();
    }

    public void setLog(ArrayList<Task> log) {
        this.log = log;
    }

    public ArrayList<Task> getLog() {
        return log;
    }

    public Task addTodo(String s) throws DukeTaskException {
        Task temp = new Todo(s, false);
        log.add(temp);
        return temp;
    }

    public Task addDeadline(String s) throws DukeTaskException {
        String by = dateFinder(s, "/by");
        String name = nameFinder(s, "/by");
        Task temp = new Deadline(name, false, by);
        log.add(temp);
        return temp;
    }

    public Task addEvent(String s) throws DukeTaskException {
        String at = dateFinder(s, "/at");
        String name = nameFinder(s, "/at");
        Task temp = new Event(name, false, at);
        log.add(temp);
        return temp;
    }

    public void list() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : log) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    // returns task marked
    public Task mark(int n) {
        Task temp = log.get(n);
        temp.mark();
        return temp;
    }

    // returns task unmarked
    public Task unmark(int n) {
        Task temp = log.get(n);
        temp.unmark();
        return temp;
    }

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
