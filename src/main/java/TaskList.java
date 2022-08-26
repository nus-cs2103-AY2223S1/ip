import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> list;
    private Ui ui;
    private Storage storage;

    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        list = new ArrayList<>();
        this.storage = storage;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void mark(Task task) throws IOException {
        task.mark();
        ui.print("This task has been marked as done -\n\t\t" + task);
        storage.rewriteFile(list);
    }

    public void unmark(Task task) throws IOException {
        task.unmark();
        ui.print("This task has been marked as not done -\n\t\t" + task);
        storage.rewriteFile(list);
    }

    public void remove(Task task) throws IOException {
        list.remove(task);
        storage.rewriteFile(list);
        ui.print("This task has been removed -\n\t\t" + task + "\n\tNow you have " + list.size() + " tasks in the list");
    }

    public void listOut() {
        System.out.println(ui.getSeparator());
        System.out.println("\tTasks in your list -");
        for(int i = 0; i < list.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println(ui.getSeparator());
    }

    public void addTask(String input, char type) throws BloopException {
        Task task;
        if(type == 'T') {
            if(input.trim().length() == 4) {
                throw new BloopException("There is no task to do");
            }
            task = new ToDo(input.substring(5));
        } else {
            int index = input.indexOf('/');
            if(type == 'E') {
                if(input.trim().length() == 5) {
                    throw new BloopException("No event specified");
                }
                task = new Event(input.substring(6, index), input.substring(index + 3));
            } else {
                if(input.trim().length() == 8) {
                    throw new BloopException("No deadline specified");
                }
                task = new Deadline(input.substring(9, index), input.substring(index + 3));
            }
        }
        list.add(task);
        try {
            storage.writeToFile(task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.print("I've added this task -\n\t\t" + task + "\n\tNow you have " + list.size() + " tasks in the list");
    }
}
