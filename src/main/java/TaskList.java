import java.io.IOException;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;
    public TaskList(List<Task> tl, Storage s) {
        this.tasks = tl;
        this.storage = s;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void listTasks(Ui ui) throws DukeException {
        if (this.tasks.size() == 0) { // List is empty
            ui.printEmptyListMessage();
        } else {
            ui.printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task curTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + curTask.toString());
            }
            ui.printLine();
        }
    }

    public void markTask(int index, Ui ui) throws IOException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsDone();
        ui.printTaskMarked(taskChosen);
        storage.refreshList(this.tasks);
    }

    public void unmarkTask(int index, Ui ui) throws IOException {
        Task taskChosen = this.tasks.get(index);
        taskChosen.markAsUndone();
        ui.printTaskUnmarked(taskChosen);
        storage.refreshList(this.tasks);
    }

    public void createToDo(String[] desc, Ui ui) throws IOException {
        Todo newToDo = new Todo(String.join(" ", desc));
        this.tasks.add(newToDo);
        ui.printToDoCreated(newToDo, getSize());
        storage.refreshList(this.tasks);
    }

    public void createDeadline(String[] desc, Ui ui) throws IOException {
        Deadline newDeadline = new Deadline(desc[0], desc[1]);
        this.tasks.add(newDeadline);
        ui.printDeadlineCreated(newDeadline, getSize());
        storage.refreshList(this.tasks);
    }

    public void createEvent(String[] desc, Ui ui) throws IOException {
        Event newEvent = new Event(desc[0], desc[1]);
        this.tasks.add(newEvent);
        ui.printEventCreated(newEvent, getSize());
        storage.refreshList(this.tasks);
    }

    public void deleteTask(int index, Ui ui) throws IOException{
        Task taskToRemove = this.tasks.get(index);
        this.tasks.remove(index);
        ui.printTaskDeleted(taskToRemove, getSize());
        storage.refreshList(this.tasks);
    }
}
