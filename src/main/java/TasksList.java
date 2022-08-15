import java.util.List;
import java.util.ArrayList;

public class TasksList {
    private final List<Task> tasksList;

    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Add To-do task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException
     */
    public void addTodo(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n" +
                    "todo <task>");
        }
        String description = command[1];
        System.out.println("Duke: Got it! Duke has added this task:");
        Task newTask = new ToDo(description);
        System.out.println(newTask);
        this.tasksList.add(newTask);
        int len = this.tasksList.size();
        String line = String.format("Duke: Now you have %d task%s in the list.",
                len, len != 1 ? "s" : "");
        System.out.println(line);
    }

    /**
     * Add Deadline task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException
     */
    public void addDeadline(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n" +
                    "deadline <task> /by <date/time>");
        }
        String[] deadline = command[1].split(" /by ", 2);
        if (deadline.length == 1) {
            throw new DukeException("Duke: Please specify the date/time of this deadline:\n" +
                    "deadline <task> /by <date/time>");
        }
        System.out.println("Duke: Got it! Duke has added this task:");
        Task newTask = new Deadline(deadline[0], deadline[1]);
        System.out.println(newTask);
        this.tasksList.add(newTask);
        int len = this.tasksList.size();
        String line = String.format("Duke: Now you have %d task%s in the list.",
                len, len != 1 ? "s" : "");
        System.out.println(line);
    }

    /**
     * Add Event task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException
     */
    public void addEvent(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n" +
                    "event <task> /at <date/time>");
        }
        String[] event = command[1].split(" /at ", 2);
        if (event.length == 1) {
            throw new DukeException("Duke: Please specify the date/time of this event:\n" +
                    "event <task> /at <date/time>");
        }
        System.out.println("Duke: Got it! Duke has added this task:");
        Task newTask = new Event(event[0], event[1]);
        System.out.println(newTask);
        this.tasksList.add(newTask);
        int len = this.tasksList.size();
        String line = String.format("Duke: Now you have %d task%s in the list.",
                len, len != 1 ? "s" : "");
        System.out.println(line);
    }

    /**
     * Prints out the list of the history of tasks
     */
    public void listTasks() {
        System.out.println("Duke: Here are the tasks in your list:");
        if (tasksList.size() == 0) {
            System.out.println("*No tasks! ^_^*");
            return;
        }
        for (int i = 0; i < tasksList.size(); i++) {
            String line = String.format("%d. %s", i + 1, tasksList.get(i));
            System.out.println(line);
        }
    }

    /**
     * Mark the task of the given id as done.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException
     */
    public void markTask(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to mark by its id:\n" +
                    "mark <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.tasksList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            this.tasksList.get(id - 1).mark();
        } catch (NumberFormatException e) {
            throw new DukeException("Duke: Please specify the task to mark by its integer id:\n" +
                    "mark <id>");
        }
    }

    /**
     * Mark the task of the given id as not done.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException
     */
    public void unmarkTask(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to unmark by its id:\n" +
                    "unmark <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.tasksList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            this.tasksList.get(id - 1).unmark();
        } catch (NumberFormatException e) {
            throw new DukeException("Duke: Please specify the task to unmark by its integer id:\n" +
                    "mark <id>");
        }

    }

    /**
     * Delete the task of the given id from the list.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException
     */
    public void deleteTask(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to be deleted by its id:\n" +
                    "delete <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.tasksList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            System.out.println("Duke: Noted. I've removed this task:");
            System.out.println(this.tasksList.get(id - 1));
            this.tasksList.remove(id - 1);
            String line = String.format("Duke: Now you have %d task%s in the list.",
                    len - 1, len - 1 != 1 ? "s" : "");
            System.out.println(line);

        } catch (NumberFormatException e) {
            throw new DukeException("Duke: Please specify the task to delete by its integer id:\n" +
                    "mark <id>");
        }
    }
}
