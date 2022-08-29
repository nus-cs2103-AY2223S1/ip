package duke.task;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.Storage;

/**
 * Represents a list of Tasks (ToDo, Deadlines, Events) given by the user.
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Checks if the TaskList is empty or not.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    /**
     * Adds Task (ToDo, Deadline, Event) to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding a task.
     * @throws DukeException if the add command is invalid.
     */
    public String addTask(String[] command, Storage storage) throws DukeException {
        String taskType = command[0];
        if (taskType.equals("todo")) {
            return addToDo(command, storage);
        } else if (taskType.equals("deadline")) {
            return addDeadline(command, storage);
        } else if (taskType.equals("event")) {
            return addEvent(command, storage);
        } else {
            return "";
        }
    }

    /**
     * Adds To-do task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding a to-do task.
     * @throws DukeException if the todo command is invalid.
     */
    public String addToDo(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify what task you wish to do:\n"
                    + "todo <description>");
        }
        StringBuilder result = new StringBuilder();
        result.append("Duke: Got it! Duke has added this task:");
        String description = command[1];
        Task newTask = new ToDo(description);
        result.append(newTask);
        this.taskList.add(newTask);
        int len = this.taskList.size();
        String line = String.format("Duke: Now you have %d task%s in the list.",
                len, len != 1 ? "s" : "");
        result.append(line);
        storage.addTaskToSave(newTask);
        return result.toString();
    }

    /**
     * Adds Deadline task to the list of tasks.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully adding a deadline.
     * @throws DukeException if the deadline command is invalid.
     */
    public String addDeadline(String[] command, Storage storage) throws DukeException {
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
            StringBuilder result = new StringBuilder();
            result.append("Duke: Got it! Duke has added this task:");
            Task newTask = new Deadline(deadline[0], deadline[1]);
            result.append(newTask);
            this.taskList.add(newTask);
            int len = this.taskList.size();
            String line = String.format("Duke: Now you have %d task%s in the list.",
                    len, len != 1 ? "s" : "");
            result.append(line);
            storage.addTaskToSave(newTask);
            return result.toString();
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
     * @return The String message for successfully adding an event.
     * @throws DukeException if the event command is invalid.
     */
    public String addEvent(String[] command, Storage storage) throws DukeException {
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
            StringBuilder result = new StringBuilder();
            result.append("Duke: Got it! Duke has added this task:");
            Task newTask = new Event(event[0], event[1]);
            result.append(newTask);
            this.taskList.add(newTask);
            int len = this.taskList.size();
            String line = String.format("Duke: Now you have %d task%s in the list.",
                    len, len != 1 ? "s" : "");
            result.append(line);
            storage.addTaskToSave(newTask);
            return result.toString();
        } catch (DateTimeException e) {
            throw new DukeException("Duke: Uh oh! Please enter your date/time in this format:\n"
                    + "dd mm yyyy hh:mm(optional)");
        }
    }

    /**
     * Prints out the list of the history of tasks
     *
     * @return The String message for successfully listing all the tasks.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();
        result.append("Duke: Here are the tasks in your list:\n");
        if (isEmpty()) {
            return result.toString() + "*No tasks! ^_^*";
        }
        for (int i = 0; i < taskList.size(); i++) {
            String line = String.format("%d. %s\n", i + 1, this.taskList.get(i));
            result.append(line);
        }
        return result.toString();
    }

    /**
     * Marks the task of the given id as done.
     *
     * @param command The command represented by an array of Strings.
     * @param storage The storage associated with this command.
     * @return The String message for successfully marking a task.
     * @throws DukeException if the mark command is invalid.
     */
    public String markTask(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to mark by its id:\n"
                    + "mark <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            storage.markTaskInSave(id - 1);
            return this.taskList.get(id - 1).mark();
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
     * @return The String message for successfully unmarking a task.
     * @throws DukeException if the unmark command is invalid.
     */
    public String unmarkTask(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to unmark by its id:\n"
                    + "unmark <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            storage.unmarkTaskInSave(id - 1);
            return this.taskList.get(id - 1).unmark();
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
     * @return The String message for successfully deleting a task.
     * @throws DukeException if the delete command is invalid.
     */
    public String deleteTask(String[] command, Storage storage) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please specify the task to be deleted by its id:\n"
                    + "delete <id>");
        }
        try {
            int id = Integer.parseInt(command[1]);
            int len = this.taskList.size();
            if (id <= 0 || id > len) {
                throw new DukeException("Duke: Invalid task id!");
            }
            StringBuilder result = new StringBuilder();
            result.append("Duke: Noted. I've removed this task:");
            result.append(this.taskList.get(id - 1));
            this.taskList.remove(id - 1);
            String line = String.format("Duke: Now you have %d task%s in the list.",
                    len - 1, len - 1 != 1 ? "s" : "");
            result.append(line);
            storage.deleteTaskFromSave(id - 1);
            return result.toString();
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
        for (Task t : this.taskList) {
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
            this.taskList.add(todo);
            if (completionStatus.equals("1")) {
                todo.completeTask();
            }
            break;
        case "D":
            String[] deadlineDate = details.split(" /by ", 2);
            Task deadline = new Deadline(deadlineDate[0], deadlineDate[1]);
            this.taskList.add(deadline);
            if (completionStatus.equals("1")) {
                deadline.completeTask();
            }
            break;
        case "E":
            String[] eventDate = details.split(" /at ", 2);
            Task event = new Event(eventDate[0], eventDate[1]);
            this.taskList.add(event);
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
     * @return The String message for successfully finding a task.
     * @throws DukeException if the find command is invalid.
     */
    public String findTask(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("Duke: Please provide a keyword to search for the task:\n"
                    + "find <keyword>");
        }
        String keyword = command[1];
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.hasKeyword(keyword)) {
                count++;
                String line = String.format("%d. %s\n", count, task);
                result.append(line);
            }
        }
        if (count > 0) {
            return "Duke: Here are the matching tasks in your list:" + result.toString();
        } else {
            return "Duke: Hm...Duke found no matching task in your list.";
        }
    }
}
