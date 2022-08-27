package duke.task;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.Storage;

/**
 * Represents a list of Tasks (ToDo, Deadlines, Events) given by the user.
 */
public class TasksList {
    private final List<Task> tasksList;

    /**
     * Creates an empty TasksList.
     */
    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Checks if the TasksList is empty or not.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasksList.size() == 0;
    }

    /**
     * Adds Task (ToDo, Deadline, Event) to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the add command is invalid.
     */
    public void addTask(String[] command, Storage storage) throws DukeException {
        String taskType = command[0];
        switch (taskType) {
        case "todo":
            addTodo(command, storage);
            break;
        case "deadline":
            addDeadline(command, storage);
            break;
        case "event":
            addEvent(command, storage);
            break;
        default:
            break;
        }
    }

    /**
     * Adds To-do task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the todo command is invalid.
     */
    public void addTodo(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n"
                    + "todo <description>");
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
        storage.addTaskToSave(newTask);
    }

    /**
     * Adds Deadline task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the deadline command is invalid.
     */
    public void addDeadline(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n"
                    + "deadline <description> /by <date/time>");
        }
        String[] deadline = command[1].split(" /by ", 2);
        if (deadline.length == 1) {
            throw new DukeException("Duke: Please specify the description and the date/time of this deadline:\n"
                    + "deadline <description> /by <date/time>");
        }
        try {
            Task newTask = new Deadline(deadline[0], deadline[1]);
            System.out.println("Duke: Got it! Duke has added this task:");
            System.out.println(newTask);
            this.tasksList.add(newTask);
            int len = this.tasksList.size();
            String line = String.format("Duke: Now you have %d task%s in the list.",
                    len, len != 1 ? "s" : "");
            System.out.println(line);
            storage.addTaskToSave(newTask);
        } catch (DateTimeException e) {
            throw new DukeException("Duke: Uh oh! Please enter your date/time in this format:\n"
                    + "dd mm yyyy hh:mm(optional)");
        }
    }

    /**
     * Adds Event task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the event command is invalid.
     */
    public void addEvent(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n"
                    + "event <description> /at <date/time>");
        }
        String[] event = command[1].split(" /at ", 2);
        if (event.length == 1) {
            throw new DukeException("Duke: Please specify the description and the date/time of this event:\n"
                    + "event <description> /at <date/time>");
        }
        try {
            Task newTask = new Event(event[0], event[1]);
            System.out.println("Duke: Got it! Duke has added this task:");
            System.out.println(newTask);
            this.tasksList.add(newTask);
            int len = this.tasksList.size();
            String line = String.format("Duke: Now you have %d task%s in the list.",
                    len, len != 1 ? "s" : "");
            System.out.println(line);
            storage.addTaskToSave(newTask);
        } catch (DateTimeException e) {
            throw new DukeException("Duke: Uh oh! Please enter your date/time in this format:\n"
                    + "dd mm yyyy hh:mm(optional)");
        }
    }

    /**
     * Prints out the list of the history of tasks
     */
    public void listTasks() {
        System.out.println("Duke: Here are the tasks in your list:");
        if (isEmpty()) {
            System.out.println("*No tasks! ^_^*");
            return;
        }
        for (int i = 0; i < tasksList.size(); i++) {
            String line = String.format("%d. %s", i + 1, this.tasksList.get(i));
            System.out.println(line);
        }
    }

    /**
     * Marks the task of the given id as done.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the mark command is invalid.
     */
    public void markTask(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to mark by its id:\n"
                    + "mark <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.tasksList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            this.tasksList.get(id - 1).mark();
            storage.markTaskInSave(id - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Duke: Please specify the task to mark by its integer id:\n"
                    + "mark <id>");
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Marks the task of the given id as not done.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the unmark command is invalid.
     */
    public void unmarkTask(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to unmark by its id:\n"
                    + "unmark <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.tasksList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            this.tasksList.get(id - 1).unmark();
            storage.unmarkTaskInSave(id - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Duke: Please specify the task to unmark by its integer id:\n"
                    + "mark <id>");
        }
    }

    /**
     * Deletes the task of the given id from the list.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @throws DukeException if the delete command is invalid.
     */
    public void deleteTask(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to be deleted by its id:\n"
                    + "delete <id>");
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
            storage.deleteTaskFromSave(id - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Duke: Please specify the task to delete by its integer id:\n"
                    + "mark <id>");
        }
    }

    /**
     * Converts the tasks in the current tasks list to StringBuilder
     *
     * @return The StringBuilder of all the tasks in the list.
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (Task t : this.tasksList) {
            sb.append(t.toCommand() + "\n");
        }
        return sb;
    }

    /**
     * Loads the given task into the task list.
     *
     * @param line The String representing the task from the save file.
     */
    public void loadFromSave(String line) {
        String[] command = line.split(" \\| ", 3);
        String taskType = command[0];
        String completionStatus = command[1];
        String details = command[2];
        switch (taskType) {
        case "T":
            Task todo = new ToDo(details);
            this.tasksList.add(todo);
            if (completionStatus.equals("1")) {
                todo.completeTask();
            }
            break;
        case "D":
            String[] deadlineDate = details.split(" /by ", 2);
            Task deadline = new Deadline(deadlineDate[0], deadlineDate[1]);
            this.tasksList.add(deadline);
            if (completionStatus.equals("1")) {
                deadline.completeTask();
            }
            break;
        case "E":
            String[] eventDate = details.split(" /at ", 2);
            Task event = new Event(eventDate[0], eventDate[1]);
            this.tasksList.add(event);
            if (completionStatus.equals("1")) {
                event.completeTask();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Searches for and lists tasks that contain the keyword in their description.
     *
     * @param command The command represented by an array of Strings.
     * @throws DukeException if the find command is invalid.
     */
    public void findTask(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please provide a keyword to search for the task:\n"
                    + "find <keyword>");
        }
        String keyword = command[1];
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < this.tasksList.size(); i++) {
            Task task = this.tasksList.get(i);
            if (task.hasKeyword(keyword)) {
                count++;
                String line = String.format("%d. %s\n", count, task);
                sb.append(line);
            }
        }
        if (count > 0) {
            System.out.println("Duke: Here are the matching tasks in your list:");
            System.out.println(sb);
        } else {
            System.out.println("Duke: Hm...Duke found no matching task in your list.");
        }
    }
}
