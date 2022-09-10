package duke;

import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {

    private ArrayList<Task> log = new ArrayList<>();
    private ArrayList<Task> findLog = new ArrayList<>();
    private boolean wasFinding = false;

    TaskList() {
    }

    public void setLog(ArrayList<Task> log) {
        this.log = log;
    }

    public ArrayList<Task> getLog() {
        return log;
    }

    public Task addTodo(String s) throws DukeTaskException {
        wasFinding = false;
        Task temp = new Todo(s, false);
        log.add(temp);
        return temp;
    }

    public Task addDeadline(String s) throws DukeTaskException {
        wasFinding = false;
        String by = dateFinder(s, "/by");
        String name = nameFinder(s, "/by");
        Task temp = new Deadline(name, false, by);
        log.add(temp);
        return temp;
    }

    public Task addEvent(String s) throws DukeTaskException {
        wasFinding = false;
        String at = dateFinder(s, "/at");
        String name = nameFinder(s, "/at");
        Task temp = new Event(name, false, at);
        log.add(temp);
        return temp;
    }

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

    // returns task marked
    public Task mark(int n) {
        Task temp = findCurrentList().get(n);
        temp.mark();
        return temp;
    }

    // returns task unmarked
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
