import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void showList() {
        this.drawLine();
        this.indentMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            this.indentMessage((i + 1) + "." + tasks.get(i).toString());
        }
        this.drawLine();
    }

    public void markAsDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        this.drawLine();
        this.indentMessage("Nice! I've marked this task as done:");
        this.indentMessage("  " + task);
        this.drawLine();
    }

    public void markAsNotDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        Task task = this.tasks.get(index - 1);
        task.markAsNotDone();
        this.drawLine();
        this.indentMessage("OK, I've marked this task as not done yet:");
        this.indentMessage("  " + task);
        this.drawLine();
    }
    
    public void indentMessage(String msg) {
        Duke.indentMessage(msg);
    }

    public void drawLine() {
        Duke.drawLine();
    }

    public void addToList(String msg) {
        Task task = new Task(msg);
        this.tasks.add(task);

        this.drawLine();
        this.indentMessage("Task successfully added!");
        this.indentMessage("  " + task);
        this.drawLine();
    }

    public void addTodo(String msg) throws DukeException {

        String description = "";
        String[] splitted = msg.split("\\s+");

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
            description += splitted[i] + " ";
        }

        Task task = new Todo(description.trim());
        this.tasks.add(task);

        this.drawLine();
        this.indentMessage("Got it. I've added this task:");
        this.indentMessage("  " + task);
        showNumberOfTasks();
        this.drawLine();
    }

    public void addDeadline(String msg) throws DukeException {
        String description = "";
        String by = "";

        String[] splitted = msg.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
            if (splitted[i].equals("/by")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                by = by + splitted[i] + " ";
            } else {
                description = description + splitted[i] + " ";
            }
        }

        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("deadline", "/by");
        }

        Task task = new Deadline(description.trim(), by.trim());
        this.tasks.add(task);

        this.drawLine();
        this.indentMessage("Got it. I've added this task:");
        this.indentMessage("  " + task);
        showNumberOfTasks();
        this.drawLine();
    }

    public void addEvent(String msg) throws DukeException {
        String description = "";
        String at = "";

        String[] splitted = msg.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
            if (splitted[i].equals("/at")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                at = at + splitted[i] + " ";
            } else {
                description = description + splitted[i] + " ";
            }
        }

        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("event", "/at");
        }

        Task task = new Event(description.trim(), at.trim());
        this.tasks.add(task);

        this.drawLine();
        this.indentMessage("Got it. I've added this task:");
        this.indentMessage("  " + task);
        showNumberOfTasks();
        this.drawLine();
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        this.drawLine();
        this.indentMessage("Noted. I've removed this task:");
        this.indentMessage("  " + task);
        showNumberOfTasks();
        this.drawLine();
    }

    public void showNumberOfTasks() {
        this.indentMessage("Now you have " + this.tasks.size() + " tasks in the list.");
    }
}
